<div class="jumbotron search-block">
    <p>
        <div class="icon-cust">
            <g:link action="show" controller="fishingArea" data-id='${r.id}' id="${r.id}">
                <h2>${r.name}</h2>
            </g:link>

            <h3><i class="fa fa-map-marker"></i> ${r.location}</h3>
        </div>
    </p>
</div>
