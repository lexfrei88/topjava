<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<a class="dropdown">
    <label for="lang"></label>
    <select class="dropdown-toggle" id="lang"
            onchange="if (this.value) window.location.href=window.location.href.split('?')[0] + '?lang=' + this.value">
        <option hidden selected>${pageContext.response.locale}</option>
        <option>ru</option>
        <option>en</option>
    </select>
</a>