/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.CommentDTO;
import dto.PostDTO;
import entity.Post;
import entity.PostComment;
import java.util.ArrayList;
import repository.PostRepository;
import util.DBContext;
import java.sql.*;
import services.UserService;

/**
 *
 * @author hunggt1572004
 */
public class PostRepositoryImpl implements PostRepository {

    @Override
    public ArrayList<Post> getAllPostOfClub(int clubId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Post> posts = new ArrayList<>();
        UserService userService = new UserService();
        try {
            String sql = """
                         Select * from posts
                         where clubId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String userAvatar = userService.getUserInfor(userId).getAvatar();
                String content = rs.getNString("content");
                String img = rs.getString("img");
                Timestamp createAt = rs.getTimestamp("createdAt");
                ArrayList<PostComment> postComment = getAllCommentOfPost(id);
                int loves = getLoves(id);
                String fullName = userService.getFullNameAndAvatar(userId).getUserName();
                Post post = new Post.PostBuilder()
                        .setClubId(clubId)
                        .setId(id)
                        .setUserId(userId)
                        .setContent(content)
                        .setImg(img)
                        .setPostCommentList(postComment)
                        .setCreatedAt(createAt)
                        .setLoves(loves)
                        .setComments(postComment.size())
                        .setFullName(fullName)
                        .setUserAvatar(userAvatar)
                        .build();
                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    private ArrayList<PostComment> getAllCommentOfPost(int postId) {
        DBContext db = DBContext.getInstance();
        ArrayList<PostComment> postComments = new ArrayList<>();

        try {
            String sql = """
                         select c.id, c.postId, c.userId, c.content, ui.avatarImg, CONCAT(ui.lastName, ' ', ui.firstName) as fullName from comments as c
                                                  join user_informations as ui on ui.userId = c.userId
                                                  where postId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, postId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                PostComment postComment = new PostComment(rs.getInt("id"), rs.getInt("postId"), rs.getInt("userId"), rs.getNString("content"), rs.getString("avatarImg"), rs.getNString("fullName"));
                postComments.add(postComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postComments;
    }

    @Override
    public void addLove(int postId, int userId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                       insert into loves(postId, userId)
                       values (?, ?)
                       """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, postId);
            statement.setInt(2, userId);
            int rs = statement.executeUpdate();
            if (rs == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            return;
        }
    }

    private int getLoves(int postId) {
        DBContext db = DBContext.getInstance();

        try {
            String sql = """
                       select count(id) as sumLoves 
                       from loves
                       where postId = ?
                       group by postId
                       """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, postId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                return rs.getInt("sumLoves");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addPost(PostDTO postDTO) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                         insert into posts
                         values (?, ?, ?, ? , ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, postDTO.getClubId());
            statement.setInt(2, postDTO.getUserId());
            statement.setNString(3, postDTO.getContent());
            statement.setTimestamp(4, postDTO.getCreatedAt());
            statement.setString(5, postDTO.getImg());
            rs = statement.executeUpdate();

            if (rs == 0) {
                throw new Exception();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isLove(int postId, int userId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select * from loves
                         where postId = ? and userId = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, postId);
            statement.setInt(2, userId);

            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        DBContext db = DBContext.getInstance();

        try {
            String sql = """
                         insert into comments(postId, userId, content, createdAt)
                         values (?, ?, ?, ?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, commentDTO.getPostId());
            statement.setInt(2, commentDTO.getUserId());
            statement.setNString(3, commentDTO.getContent());
            statement.setTimestamp(4, commentDTO.getCreatedAt());

            int rs = statement.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

}
