<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Localgram</title>

    <!-- 제이쿼리 -->
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

    <!-- Style -->
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/story.css">
    <link rel="stylesheet" href="/css/popular.css">
    <link rel="stylesheet" href="/css/profile.css">
    <link rel="stylesheet" href="/css/upload.css">
    <link rel="stylesheet" href="/css/update.css">
    <link rel="shortcut icon" href="/images/insta.svg">

    <!-- Fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
</head>

<body>

    <header class="header">
        <div class="container">
            <a href="/image/story" class="logo">
                <img src="/images/logo.jpg" alt="">
            </a>
            <nav class="navi">
                <ul class="navi-list">
                    <li class="navi-item">
                        <a href="/image/story">
                            <i class="fas fa-home"></i>
                        </a>
                    </li>
                    <li class="navi-item">
                        <a href="/image/popular">
                            <i class="fas fa-compass"></i>
                        </a>
                    </li>
                    <li class="navi-item">
                        <a href="/image/profile">
                            <i class="fas fa-user"></i>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </header>

</body>