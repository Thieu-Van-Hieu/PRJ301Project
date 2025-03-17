<%@page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hoaroi.css" />
    <style>
        .petal {
            position: absolute;
            width: 20px;
            height: 20px;
            background: url('${pageContext.request.contextPath}/assets/img/effect-img/hoaanhdao.png') no-repeat center center;
            background-size: contain;
            opacity: 0.9;
            animation: fall linear infinite;
        }
    </style>
</head>
<body>
    <div id="petalContainer"></div>
    <script src="${pageContext.request.contextPath}/assets/js/effect-js/roihoa.js"></script>
</body>
