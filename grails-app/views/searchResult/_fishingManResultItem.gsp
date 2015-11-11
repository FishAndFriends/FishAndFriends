<div class="panel panel-success">
    <div class="panel-heading">
        <g:link action="show" data-id="${r.id}" controller="fishingMan" id="${r.id}">
            <h4><i class="fa ${session.fishingMan.gender.equals("H") ? 'fa-male':'fa-female'}"></i> ${r.firstname} ${r.lastname}</h4>
        </g:link>
    </div>

    <div class="panel-body">
        <i class="fa fa-envelope-o"></i> ${r.email}
    </div>
</div>