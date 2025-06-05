<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Available Restaurants</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .no-data {
            text-align: center;
            font-style: italic;
            color: #888;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Available Restaurants</h2>
    <c:choose>
        <c:when test="${not empty restaurantList}">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Cuisine Type</th>
                        <th>Address</th>
                        <th>Ratings</th>
                        <th>Delivery Time</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <tr>
                            <td>${restaurant.name}</td>
                            <td>${restaurant.cuisineType}</td>
                            <td>${restaurant.address}</td>
                            <td>${restaurant.ratings}</td>
                            <td>${restaurant.deliveryTime}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${restaurant.isActive}">
                                        Active
                                    </c:when>
                                    <c:otherwise>
                                        Inactive
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="no-data">No restaurants available.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
