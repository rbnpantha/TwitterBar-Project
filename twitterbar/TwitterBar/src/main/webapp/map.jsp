<jsp:include page="Header.jsp"/>
<div id="map" class="col-xs- container-fluid"></div>
<script>
    var map;
    var marker;
    var infowindow;
    var markersArray = [];
    function initMap() {
        var latitude;
        var longitude;
        var location;

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            console.log("Geolocation is not supported by this browser.");
        }
        function showPosition(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            console.log(parseFloat(latitude));
            console.log(parseFloat(longitude));
            showMap(latitude, longitude);
        }
        function showMap(latitude, longitude) {
            console.log(parseFloat(latitude));
            console.log(parseFloat(longitude));
            location = {lat: latitude, lng: longitude};
            map = new google.maps.Map(document.getElementById('map'), {
                center: location,
                zoom: 7,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });
            marker = new google.maps.Marker({
                position: location,
                map: map,
                draggable: true,
                animation: google.maps.Animation.DROP,
                title: 'TwitterBar Project'
            });
            infowindow = new google.maps.InfoWindow({
            });
            fetchTrends(latitude, longitude);
            infowindow.open(map, marker);
            markersArray.push(marker);

            marker.addListener('click', function () {
                fetchTrends(latitude, longitude);
                infowindow.open(map, marker);
            });

            google.maps.event.addListener(map, "click", function (event)
            {
                latitude = event.latLng.lat;
                longitude = event.latLng.lng;
                location = event.latLng;
                placeMarker(latitude, longitude);
                document.getElementById("lngFld").value = event.latLng.lng();
            });
        }
        function placeMarker(latitude, longitude) {
            deleteOverlays();
            marker = new google.maps.Marker({
                position: location,
                map: map
            });
            fetchTrends(latitude, longitude);
            infowindow.open(map, marker);
            markersArray.push(marker);
            function deleteOverlays() {
                if (markersArray) {
                    for (i in markersArray) {
                        markersArray[i].setMap(null);
                    }
                    markersArray.length = 0;
                }
            }
        }
        function fetchTrends(latitude, longitude) {
            $.ajax('mapServlet', {
                'type': 'post',
                'data': {
                    'latitude': latitude,
                    'longitude': longitude
                },
                'dataType': "json"
            })
                    .done(setTrends)
                    .fail(ajaxFailure);
        }

        function ajaxFailure(jsonobj) {
            alert("Server Error");
        }
        function setTrends(jsonobj) {
            var newDiv = $('<ul>').attr({
                id: "infoWin"
            });
            $.each(jsonobj, function (idx, data) {
                console.log(data);
                newDiv.append($("<li>", {class: "listing"}).append($("<a>").attr({
                    href: data.linkUrl

                }).html(data.name)));
            });
            infowindow.setContent(newDiv.html());
            trendsContent = JSON.parse(jsonobj);
            setTrendsContents(jsonobj);
        }
        function setTrendsContents(trendsContent) {
            this.trendsContent = trendsContent;
        }
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQcgRSAUmGes-JYg3Hq8O0C48-agnkyts&callback=initMap">
</script>
<jsp:include page="footer.jsp"/>