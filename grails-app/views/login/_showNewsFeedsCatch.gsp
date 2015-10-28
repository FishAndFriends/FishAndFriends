<g:link action="show" controller="fishingMan" id="${singleCatch.fishingMan.id}">
    <h3><i class="fa fa-user"></i> ${singleCatch.fishingMan.firstname} ${singleCatch.fishingMan.lastname}</h3>
</g:link>
<p>
    <i class="flaticon-fish13"></i> ${singleCatch.fish.name}
        (${singleCatch.weight}kg, ${singleCatch.size} cm),
        le <i>${singleCatch.date.dateString}</i><br/>
    <i class="fa-location-arrow"></i> ${singleCatch.fishingArea.name}, ${singleCatch.fishingArea.location}<br/>
</p>


%{--</div>--}%