<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<a class="dropdown">
    <select class="dropdown-toggle"
            onchange="if (this.value) window.location.href=window.location.href.split('?')[0] + '?lang=' + this.value">
        <option hidden selected>${pageContext.response.locale}</option>
        <option>ru</option>
        <option>en</option>
    </select>
</a>