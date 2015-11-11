<div class="panel panel-success">
    <div class="panel-heading">
        <g:link action="show" controller="fishingArea" data-id='${r.id}' id="${r.id}">
        <h4><i class="fa fa-location-arrow"></i> ${r.name}</h4></g:link>
    </div>

    <div class="panel-body">
        <i class="fa fa-map-marker"></i> ${r.location},
        <i class="fa fa-user"></i> ${r.fishingMen.size()}
    </div>
</div>
