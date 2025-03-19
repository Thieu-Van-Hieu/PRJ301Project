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
            pageContext.setAttribute("clubId", 101);
            Member member = new Member.Builder().setId(1).setName("Hiếu").build();
            pageContext.setAttribute("member", member);
            ArrayList<MessageResponse> messages = new ArrayList<>();

            // Sử dụng Builder Pattern để tạo các đối tượng Message và thêm vào danh sách
            messages.add(new MessageResponse(1, 101, 1, "Hiếu", "Hello World!"));
            messages.add(new MessageResponse(2, 101, 2, "John", "How are you?"));
            messages.add(new MessageResponse(3, 101, 1, "Hiếu", "Java is fun!"));
            messages.add(new MessageResponse(4, 102, 3, "Alice", ""));
            messages.add(new MessageResponse(5, 102, 2, "John", "Testing messages..."));
            messages.add(new MessageResponse(6, 101, 1, "Hiếu", ""));
            messages.add(new MessageResponse(7, 102, 3, "Alice", "Another test message."));
            messages.add(new MessageResponse(8, 101, 2, "John", "Keep learning!"));
            messages.add(new MessageResponse(9, 103, 4, "Bob", "Practice makes perfect."));
            messages.add(new MessageResponse(10, 103, 4, "Bob", "End of test messages."));
            pageContext.setAttribute("messages", messages);
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
            };

            function getMessageNode(message) {
                // Tạo phần tử div chính
                let messageNode = document.createElement("div");
                messageNode.className = "message " + (message.memberName === "${member.name}" ? "self" : "other" ) + (message.message === "" ? " message--disabled" : "");
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
                return message.clubId == "${clubId}";
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
                let id = messageElement?.getAttribute("data-message-id");
                let clubId = "${clubId}";
                let memberId = "${member.id}";
                let memberName = "${member.name}";
                let content = "";
                
                if (action === "createMessage") {
                    content = messageElement.querySelector(".message__content").value;
                }

                let myMessage = new Message(id, clubId, memberId, memberName, content);
                let message = {
                    action: action,
                    data: myMessage,
                }
                socket.send(JSON.stringify(message));
            }  
        </script>
    </head>
    <body>
        <jsp:include page="contentHeader.jsp" />
        <div class="chat-box">
            <div class="messages">
                <c:forEach var="message" items="${messages}">
                <div class="message ${message.getMemberName() == member.name ? 'self' : 'other'} ${message.getContent() == '' ? 'message--disabled' : ''}" data-message-id="${message.getId()}">
                    <span class="message__controls">
                        <div class="message__control btn__delete" onclick="sendMessage(this, 'deleteMessage')">Gỡ</div>
                        <div class="message__control btn__more">⋮</div>
                    </span>
                    <div class="message__description">
                        <span class="message__username">${message.getMemberName()}</span>
                        <span class="message__content">${message.getContent() == "" ? "Đã gỡ" : message.getContent()}</span>
                    </div>
                </div>
                </c:forEach>
            </div>

            <div class="message-container">
                <input type="text" class="message__input" placeholder="Nhập tin nhắn...">
                <button class="message__send" onclick="sendMessage(this, 'createMessage')">➤</button>
            </div>
            <%-- <form action="/MessageServlet" method="post">
            </form> --%>
        </div>

        <script>
            const messagesContainer = $('.messages');

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

            // Kiểm tra xem có phải ở chế độ tạo nhiệm hay không
            let action = "${action}";
            if (action === "createMessage") {
                let message = document.querySelector(".messages .message:last-child");
                sendMessage(message, action);
            }

        </script>

        <c:if test="${action != null}">
            <c:set var="action" value="${''}"/>
        </c:if>
    </body>
</html>
