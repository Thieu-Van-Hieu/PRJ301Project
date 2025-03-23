<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="entity.*"%>
<%@page import="java.util.*"%>
<%@page import="dto.MessageResponse"%>
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

            .message.message--disabled .message__controls {
                display: none;
            }

            .message.message--disabled .message__content {
                color: #000;
                background-color: #ccc;
            }

            .message.self {
                align-self: end;
            }

            .message .message__description {
                display: flex;
                flex-direction: column;
                margin: 10px;
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

            .message__controls {
                display: flex;
                gap: 10px;
            }

            .message__control {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 32px;
                width: 32px;
                border-radius: 50%;
                font-size: 1.6rem;
                color: #fff;
                background-color: #77cbe2;
                cursor: pointer;
            }

            .btn__delete {
                display: none;
                background-color: #ff3b30;
                border-radius: 10px;
            }

            .message__controls:hover .btn__delete {
                display: flex;
            }

            .message.other .message__controls {
                display: none;
            }

            .message__username {
                padding: 10px 10px 0 10px;
                margin-bottom: 10px;
                font-size: 1.2rem;
            }

            .message__content {
                flex: 1;
                padding: 10px;
                font-size: 1.6rem;
            }
            
            .message-container {
                display: flex;
                margin: 10px;
                border-radius: 5px;
                background-color: #a1a6a4;
            }

            .message__input {
                flex: 1;
                padding: 10px 0 10px 10px;
                color: #fff;
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
        <% 
            // Tạo các biến session
            // Member member = new Member.Builder().setId((int)(Math.random() * 2) + 1).build();
            // pageContext.setAttribute("member", member);
            request.setAttribute("contentHeader", "Messenger");
        %>

        <script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/dto/Message.js"></script>
        <script>
            // Kết nối WebSocket đến Server
            var socket = new WebSocket("ws://" + window.location.host + "${pageContext.request.contextPath}/chat");

            // Khi kết nối thành công
            socket.onopen = function () {
                console.log("Kết nối WebSocket thành công!");
                let action = "${action}";
                console.log("Action: ", action);
                if (action === "createMessage") {
                    console.log("Gửi tin nhắn");
                    let message = document.querySelector(".messages .message:last-child");
                    sendMessage(message, action);
                }
            };

            function getMessageNode(message) {
                // Tạo phần tử div chính
                let messageNode = document.createElement("div");
                messageNode.className = "message " + (message.memberId === "${member.id}" ? "self" : "other" ) + (message.message === "" ? " message--disabled" : "");
                messageNode.setAttribute("data-message-id", message.id);

                // Tạo các phần tử con bên trong div
                messageNode.innerHTML = `
                    <span class="message__controls">
                        <div class="message__control btn__delete" onclick="sendMessage(this, 'deleteMessage')">Gỡ</div>
                        <div class="message__control btn__more">⋮</div>
                    </span>
                    <div class="message__description">
                        <span class="message__username">` + message.memberName + `</span>
                        <span class="message__content">` + (message.message == "" ? "Đã gỡ" : message.message) + `</span>
                    </div>
                `;

                return messageNode;
            }

            function isUserAtBottom() {
                let messageContainer = $(".chat-box .messages");
                console.log(messageContainer.scrollHeight - messageContainer.scrollTop - messageContainer.clientHeight);
                return Math.abs(messageContainer.scrollHeight - messageContainer.scrollTop - messageContainer.clientHeight) < 5;
            }

            function scrollToBottom() {
                let messageContainer = $(".chat-box .messages");
                messageContainer.scrollTop = messageContainer.scrollHeight;
            }

            function createMessage(message) {
                let messageContainer = $(".chat-box .messages");

                if (messageContainer.querySelector(`.message[data-message-id="` + message.id + `"]`)) {
                    return;
                }

                let isBottom = isUserAtBottom();

                messageContainer.appendChild(getMessageNode(message));

                if (isBottom) {
                    console.log("Cuộn xuống dưới cùng");
                    scrollToBottom();
                }
            }

            function deleteMessage(message) {
                console.log("Xóa tin nhắn: ", message);
                let messageContainer = $(".chat-box .messages");
                let messageNode = messageContainer.querySelector(`.message[data-message-id="` + message.id + `"]`);
                messageContainer.replaceChild(getMessageNode(message), messageNode);
            }

            function isMessageSentToMe(message) {
                return message.clubId == "${member.clubId}";
            }

            // Khi nhận tin nhắn từ server
            socket.onmessage = function (event) {
                let message = JSON.parse(event.data);
                console.log("Nhận tin nhắn từ Server: ", message.data);

                if (!isMessageSentToMe(message.data)) {
                    return;
                }

                let actionHandlers = new Map([
                    ["createMessage", createMessage],
                    ["deleteMessage", deleteMessage]
                ]);

                actionHandlers.get(message.action)(message.data);
            };

            // Khi xảy ra lỗi
            socket.onerror = function (error) {
                console.log("Lỗi WebSocket: " + error);
            };

            // Khi WebSocket bị đóng
            socket.onclose = function () {
                console.log("WebSocket bị đóng!");
            };

            // Gửi tin nhắn lên Server
            function sendMessage(element, action) {
                event.stopPropagation(); // Ngăn chặn sự kiện click lan ra ngoài
                let messageElement = element.closest(".message");
                console.log("Element:", element);
                let id = messageElement?.getAttribute("data-message-id");
                let clubId = "${member.clubId}";
                let memberId = "${member.id}";
                let memberName = "${userInformation.firstName}";
                let content = "";
                
                if (action === "createMessage") {
                    content = messageElement.querySelector(".message__content").textContent;
                }

                let myMessage = new Message(id, clubId, memberId, memberName, content);
                console.log(myMessage);
                let message = {
                    action: action,
                    data: myMessage,
                }
                socket.send(JSON.stringify(message));

                if (action === "deleteMessage") {
                    element.closest("form").submit();
                }
            }  
        </script>
    </head>
    <body>
        <jsp:include page="contentHeader.jsp" />
        <div class="chat-box">
            <div class="messages">
                <c:forEach var="message" items="${messages}">
                <div class="message ${message.memberId == member.id ? 'self' : 'other'} ${message.message == '' ? 'message--disabled' : ''}" data-message-id="${message.getId()}">
                    <span class="message__controls">
                        <form action="${pageContext.request.contextPath}/MessengerServlet" method="post">
                            <input type="hidden" name="action" value="deleteMessage">
                            <input type="hidden" name="messageId" value="${message.getId()}">
                            <div class="message__control btn__delete" onclick="sendMessage(this, 'deleteMessage')">Gỡ</div>
                        </form>
                        <div class="message__control btn__more">⋮</div>
                    </span>
                    <div class="message__description">
                        <span class="message__username">${message.getMemberName()}</span>
                        <span class="message__content">${message.message == "" ? "Đã gỡ" : message.message}</span>
                    </div>
                </div>
                </c:forEach>
            </div>

            <form action="${pageContext.request.contextPath}/MessengerServlet" method="post">
                <input type="hidden" name="action" value="createMessage">
                <div class="message-container">
                    <input type="text" class="message__input" placeholder="Nhập tin nhắn..." name="content" required autocomplete="off" />
                    <button class="message__send">➤</button>
                </div>
            </form>
        </div>

        <script>
            const messagesContainer = $('.messages');

            // Cuộn xuống cuối cùng khi load trang
            function scrollToBottom() {
                messagesContainer.scrollTop = messagesContainer.scrollHeight;
            }

            scrollToBottom();

            // Kiểm tra xem người dùng có nhập tin nhắn mới không
            function checkNewMessage(element) {
                let messageInput = document.querySelector('.message__input');
                let message = messageInput.value.trim();
                if (message !== '') {
                    sendMessage(messageInput.closest('form'), 'createMessage');
                    messageInput.value = '';
                }
            }
        </script>

        <c:if test="${action != null}">
            <c:set var="action" value="${''}"/>
        </c:if>
    </body>
</html>
