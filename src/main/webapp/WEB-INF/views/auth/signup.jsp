<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prolog</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
</head>

<body>
    <div class="container">
        <main class="loginMain">
           <!--회원가입섹션-->
            <section class="login">
                <article class="login__form__container">
                  
                   <!--회원가입 폼-->
                    <div class="login__form">
                        <!--로고-->
                        <h1><img src="/images/logo_t.jpg" alt=""></h1>
                         <!--로고end-->
                         
                         <!--회원가입 인풋-->
                        <form class="login__input" action="/auth/signup" method="POST" >
                            <input type="text" name="username" placeholder="USERNAME" required="required" maxlength="20" />
                            <input type="password" name="password" placeholder="PASSWORD" required="required" />
                            <input type="email" name="email" placeholder="E-MAIL" required="required" />
                            <input type="text" name="name" placeholder="NAME" required="required" />
                            <button>가입</button>
                        </form>
                        <!--회원가입 인풋end-->
                    </div>
                    <!--회원가입 폼end-->
                    
                    <!--계정 있?-->
                    <div class="login__register">
                        <span>계정이 있으신가요?</span>
                        <a href="/auth/signin">로그인</a>
                    </div>
                    <!--계정 있?end-->
                    
                </article>
            </section>
        </main>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</body>

</html>