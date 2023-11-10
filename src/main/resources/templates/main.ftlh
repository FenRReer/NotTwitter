<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="row g-3">
        <div class="col-md-6">
            <form method="get" action="/main" class = "form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}">
                <button type="submit" class="btn btn-primary m-2">Найти</button>
            </form>
        </div>
    </div>

    <#include "parts/messageEdit.ftlh">

    <#include "parts/messageList.ftlh">

</@c.page>