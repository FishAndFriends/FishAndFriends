<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fish & Friends</title>
    <meta name="layout" content="main"/>
</head>

<body>
    <div class="container-fluid">
        <g:if test="${error}">
            <h3 class="error">Une erreur est survenue lors de la recherche</h3>
        </g:if>
        <g:else>
            <!-- Show result with their template -->
            <g:if test="${result.size > 0}">
                <g:each in="${result}" var="r">
                    <g:if test="${r instanceof fishandfriends.FishingMan}">
                        <g:render template="fishingManResultItem" model="[object: r]"/>
                    </g:if>
                    <g:elseif test="${r instanceof fishandfriends.FishingArea}">
                        <g:render template="fishingAreaResultItem" model="[object: r]"/>
                    </g:elseif>
                    <g:elseif test="${r instanceof fishandfriends.Fish}">
                        <g:render template="fishResultItem" model="[object: r]"/>
                    </g:elseif>
                    <g:else>
                        ${r}
                    </g:else>
                    <br/>
                </g:each>
            </g:if>
            <g:if test="${result.size == 0}">
                <p class="warning-text">Aucun résultat</p>
            </g:if>

            <!-- Pagination buttons -->
            <span class="input-group-btn pagination-btn">
                <g:link action="prevPage" controller="searchResult" params="[page:page,want:want, query:query]">
                    <button class="btn btn-default" <g:if test="${params.page == 0}">disabled</g:if>>Précédent</button>
                </g:link>
                - ${page + 1}
                <g:link action="nextPage" controller="searchResult" params="[page:page,want:want, query:query]">
                    <button class="btn btn-default" style="margin-left: 5px;"<g:if test="${!params.hasMore}">disabled</g:if>>Suivant</button>
                </g:link>
            </span>

            <!-- Restore values on the search field -->
            <script>
                $('#want').val('${want}');
                $('#query').val('${query}');
            </script>
        </g:else>
    </div>
</body>
</html>