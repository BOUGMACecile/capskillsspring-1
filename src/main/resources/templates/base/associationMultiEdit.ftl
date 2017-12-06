
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<html>
<body>
    <h1>${page}</h1>
    <table class="table table-bordered table-hover">
        <#include "../includable/formShowContent.ftl">
    </table>
    <#include "../includable/associationMultiTableForm.ftl">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <a href="../index">Back</a>
</body>
</html>