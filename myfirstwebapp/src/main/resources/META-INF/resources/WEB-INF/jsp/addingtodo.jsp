
<body>
<%@ include file="common/navigation.jspf" %>
<%@ include file="common/header.jspf" %>

<div class="container mt-3">
<h1>Enter Todo Details</h1>

<fieldset class="mb-3">
<form:form action="" method="post" modelAttribute="todo">
<fieldset class="mb-3">
<form:label path="description">Description</form:label>
 <form:input type="text" path="description" required="required"/>
<form:errors  path="description"  class="text text-warning"/>
</fieldset>

   
<fieldset class="mb-3">
<form:label path="targetDate">Target Date</form:label>
 <form:input type="text" path="targetDate" required="required"/>
<form:errors  path="targetDate"  class="text text-warning"/>
</fieldset>
   
   
   
    <form:input type="hidden" path="id"/>
    <form:input type="hidden" path="done"/>
<input type="submit" class="btn btn-success">
</form:form>

</div>

<%@ include file="common/footer.jspf" %> 
