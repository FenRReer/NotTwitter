<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page></@p.pager>
<div class="grid" data-masonry='{ "itemSelector": ".grid-item"}'>
    <#list page.content as message>
        <div class="card m-3 grid-item card-top" style="width: 18rem;">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br>
                <i>#${message.tag}</i>
            </div>
            <div class="card-foot card-footer text-muted conteiner" style="background-color: #BCA37F">
                <div class="row">
                    <a style="color: #113946;" class="col d-flex justify-content-around" href="/user-messages/${message.author.id}">${message.authorName}</a>
                    <a style="color: #113946;" class="col d-flex justify-content-around" href="/messages/${message.id}/like">
                        <#if message.meLiked>
                            <i class="fa-solid fa-heart" style="color: #113946;"></i>
                        <#else>
                            <i class="fa-regular fa-heart" style="color: #113946;"></i>
                        </#if>
                        ${message.likes}
                    </a>
                    <#if message.author.id == currentUserId>
                        <a class=" col btn btn-info float-right"
                           href="/user-messages/${message.author.id}?message=${message.id}">
                            Edit
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>

<@p.pager url page></@p.pager>