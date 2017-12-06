
<head>
    <#include "../includable/bootstrap.ftl">
    <#include "../includable/jquery.ftl">
    <#include "../includable/baseFieldValidator.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <form id="createForm" action="" method="POST">
        <#include "../includable/formCreateContent.ftl">
        <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
        <br>
            <input type="submit" value="submit"/>
        </br>
    </form>
    <a href="${basePage}">Back</a>
</body>
</html>