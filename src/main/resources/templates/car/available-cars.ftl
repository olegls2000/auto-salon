<#import "../common/page.ftl" as common>
<@common.page>
    <h2> Available Cars:</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Manufacturer</th>
            <th>Fuel Type</th>
            <th>Color</th>
            <th>Body Type</th>
            <th>Action</th>
        </tr>
        </thead>
        <#list cars as car>
            <tr>
                <td>${car.manufacturer}</td>
                <td>${car.fuelType}</td>
                <td>${car.color}</td>
                <td>${car.bodyType}</td>
                <td> <a class="btn btn-primary" href="car/sell?id=${car.id}" role="button">Buy Car</a> </td>
            </tr>
        </#list>
    </table>

</@common.page>