<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Messenger Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <style>
            .chat-box {
                display: flex;
                height: 100%;
                width: 100%;
                flex-direction: column;
                overflow: auto;
                background-color: #f0f0f0;
            }

            .messages {
                display: flex;
                flex-direction: column;
                flex: 1;
                overflow-y: auto;
                outline: none;
            }

            .message {
                display: flex;
                align-items: center;
                margin: 10px;
            }

            .message.self {
                align-self: end;
            }

            .message .message__description {
                display: flex;
                flex-direction: column;
                margin: 10px;
                padding: 10px;
                border-radius: 5px;
                background-color: #38b6ff;
                color: #fff;
            }

            .message.self .message__username {
                display: none;
            }

            .message.other {
                align-self: start;
            }

            .message.other .message__btn {
                display: none;
            }

            .message__btn {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 32px;
                width: 32px;
                margin-right: 10px;
                border-radius: 50%;
                font-size: 1.6rem;
                color: #fff;
                background-color: #77cbe2;
                cursor: pointer;
            }
            .message__username {
                margin-bottom: 10px;
                font-size: 1.2rem;
            }

            .message__content {
                flex: 1;
                font-size: 1.6rem;
            }

            .message__control {
                display: none;
                align-items: center;
                justify-content: center;
                margin-right: 10px;
                height: 32px;
                width: 32px;
                border-radius: 5px;
                font-size: 1.6rem;
                color: #fff;
                background-color: #ff3d00;
                cursor: pointer;
            }

            .message__control--active {
                display: flex;
            }

            .message-container {
                display: flex;
                margin: 10px;
                border-radius: 5px;
                background-color: #a1a6a4;
            }

            .message__input {
                flex: 1;
                padding: 10px;
                border: none;
                outline: none;
                font-size: 1.6rem;
                background-color: transparent;
            }

            .message__input::placeholder {
                color: #fff;
            }

            .message__send {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 48px;
                width: 48px;
                margin-left: 10px;
                border: none;
                font-size: 1.6rem;
                background-color: transparent;
                color: #fff;
            }

            /* Responsive cho màn hình nhỏ hơn 768px (điện thoại) */
            @media (max-width: 768px) {
                .chat-box {
                    flex-direction: column;
                    height: 100vh; /* Chiều cao toàn màn hình */
                }

                .messages {
                    flex: 1;
                    padding: 10px;
                    font-size: 1.4rem; /* Giảm kích thước chữ */
                }

                .message {
                    flex-direction: column; /* Sắp xếp tin nhắn theo cột */
                    align-items: flex-start;
                }

                .message.self {
                    align-self: flex-end;
                }

                .message__description {
                    font-size: 1.4rem; /* Giảm kích thước chữ */
                    padding: 8px;
                }

                .message__username {
                    font-size: 1.2rem; /* Giảm kích thước tên người dùng */
                }

                .message__content {
                    font-size: 1.4rem; /* Giảm kích thước nội dung tin nhắn */
                }

                .message-container {
                    flex-direction: row;
                    padding: 10px;
                    gap: 10px;
                }

                .message__input {
                    font-size: 1.4rem; /* Giảm kích thước chữ trong ô nhập */
                    padding: 8px;
                }

                .message__send {
                    font-size: 1.4rem; /* Giảm kích thước nút gửi */
                    height: 40px;
                    width: 40px;
                }
            }

            /* Responsive cho màn hình nhỏ hơn 1024px (máy tính bảng) */
            @media (max-width: 1024px) {
                .chat-box {
                    height: 90vh; /* Chiều cao 90% màn hình */
                }

                .messages {
                    padding: 15px;
                    font-size: 1.6rem; /* Điều chỉnh kích thước chữ */
                }

                .message__description {
                    font-size: 1.6rem; /* Điều chỉnh kích thước chữ */
                    padding: 10px;
                }

                .message__username {
                    font-size: 1.4rem; /* Điều chỉnh kích thước tên người dùng */
                }

                .message__content {
                    font-size: 1.6rem; /* Điều chỉnh kích thước nội dung tin nhắn */
                }

                .message-container {
                    padding: 15px;
                    gap: 15px;
                }

                .message__input {
                    font-size: 1.6rem; /* Điều chỉnh kích thước chữ trong ô nhập */
                    padding: 10px;
                }

                .message__send {
                    font-size: 1.6rem; /* Điều chỉnh kích thước nút gửi */
                    height: 48px;
                    width: 48px;
                }
            }
        </style>
    </head>
    <body>
        <% 
            pageContext.setAttribute("username", "user1");
            ArrayList<Message> messages = new ArrayList<>();

            // Thêm các đối tượng Message vào danh sách (có username trùng nhau)
            messages.add(new Message(1, "user1", "Hello World!"));
            messages.add(new Message(2, "user2", "How are you?"));
            messages.add(new Message(3, "user1", "Java is fun!"));
            messages.add(new Message(4, "user3", "ArrayList test message."));
            messages.add(new Message(5, "user2", "Testing messages..."));
            messages.add(new Message(6, "user1", "This is a sample message."));
            messages.add(new Message(7, "user3", "Another test message."));
            messages.add(new Message(8, "user2", "Keep learning!"));
            messages.add(new Message(9, "user4", "Practice makes perfect."));
            messages.add(new Message(10, "user4", "End of test messages."));
            pageContext.setAttribute("messages", messages);
            request.setAttribute("contentHeader", "Messenger");
        %>
        <jsp:include page="contentHeader.jsp" />
        <div class="chat-box">
            <div class="messages">
                <c:forEach var="message" items="${messages}">
                <div class="message ${message.getUsername() == username ? 'self' : 'other'}">
                    <div class="message__control">Gỡ</div>
                    <span class="message__btn" onclick="showDeleteBtn(this)">⋮</span>
                    <div class="message__description">
                        <span class="message__username">${message.getUsername()}</span>
                        <span class="message__content">${message.getContent()}</span>
                    </div>
                </div>
                </c:forEach>
            </div>

            <div class="message-container">
                <input type="text" class="message__input" placeholder="Nhập tin nhắn...">
                <button class="message__send">➤</button>
            </div>
        </div>

        <script>
            function showDeleteBtn(element) {
                element.closest('.message').querySelector('.message__control').classList.toggle('message__control--active');
            }

            const messagesContainer = document.querySelector('.messages');

            // Cuộn xuống cuối cùng khi load trang
            function scrollToBottom() {
                messagesContainer.scrollTop = messagesContainer.scrollHeight;
            }

            scrollToBottom();

            // Giả lập load thêm tin nhắn khi cuộn lên
            messagesContainer.addEventListener('scroll', function () {
                if (messagesContainer.scrollTop === 0) {
                    loadOlderMessages();
                }
            });

            // Hàm giả lập load tin nhắn cũ
            function loadOlderMessages() {
                const newMessage = document.createElement('div');
                newMessage.classList.add('message');
                newMessage.textContent = 'Old message ' + Math.random().toFixed(2);
                messagesContainer.prepend(newMessage);
            }

        </script>
    </body>
</html>
