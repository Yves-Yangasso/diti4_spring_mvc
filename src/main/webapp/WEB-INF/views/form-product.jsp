<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<form:form action="${pageContext.request.contextPath}/produit" method="post" modelAttribute="produit">
    <form:hidden path="id"/>
    <label for="libelle">Libelle</label>
    <form:input path="libelle" id="libelle"/>
    <form:errors path="libelle" cssStyle="color:red"/>

    <label for="prix">Prix</label>
    <form:input path="prix" id="prix" type="number" step="0.01"/>
    <form:errors path="prix" cssStyle="color:red"/>

    <button type="submit">Enregistrer</button>
</form:form>
