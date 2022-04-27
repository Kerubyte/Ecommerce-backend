# Ecommerce-backend

Initial release for E-commerce Shop Backend. API let's you get all Products, get a Products by id and handle User registration and login with JWT. 
Soon endpoints will be added to supply getting User details, updating User, adding products to Cart, creating Orders and getting User Orders.


## Technologies and Tools

![Kotlin-badge](https://img.shields.io/badge/-Kotlin-47A248?logo=Kotlin&labelColor=494949&logoColor=ffffff)
![Ktor-badge](https://img.shields.io/badge/-Ktor-47A248?logo=Kotlin&labelColor=494949&logoColor=ffffff)
![Mongo-badge](https://img.shields.io/badge/-MongoDB-47A248?logo=MongoDB&labelColor=494949&logoColor=ffffff)
![JWT-badge](https://img.shields.io/badge/-JWT-47A248?logo=JSON%20Web%20Tokens&labelColor=494949&logoColor=ffffff)
![MockK-badge](https://img.shields.io/badge/-MockK-47A248?logo=Momenteo&labelColor=494949&logoColor=ffffff)

## Contents

- Product Routes
- User Routes


## Product

#### Get Products

You can request all Products in the database.

`GET` `/products`

###### Example Request


```
curl -X GET http://0.0.0.0:8080/api/products
```

###### Example Response

```
{
    "data": [
        {
            "_id": "1",
            "name": "Chefman 6-in-1 Espresso Machine Powerful 15-Bar Pump",
            "brand": "Chefman",
            "description": "This 6-in-1 coffee machine creates all of your favorite high-quality coffee beverages right in your kitchen. Enjoy single or double shots of espresso, cappuccinos, lattes, and more with the integrated frothing system. Ditch your old coffee pot, get the upgrade you’ve been craving.",
            "category": "Equipment",
            "imageUrl": "https://firebasestorage.googleapis.com/v0/b/ecommerce-3dffb.appspot.com/o/krups-intuition-preference.png?alt=media&token=5ba8dbf9-6291-40a2-867b-8ff39a20b70f",
            "price": 179.99
        },
        {
            "_id": "2",
            "name": "Cimbali Power Espresso Machine",
            "brand": "Cimbali",
            "description": "This 6-in-1 coffee machine creates all of your favorite high-quality coffee beverages right in your kitchen. Enjoy single or double shots of espresso, cappuccinos, lattes, and more with the integrated frothing system. Ditch your old coffee pot, get the upgrade you’ve been craving.",
            "category": "Equipment",
            "imageUrl": "https://firebasestorage.googleapis.com/v0/b/ecommerce-3dffb.appspot.com/o/krups-intuition-preference.png?alt=media&token=5ba8dbf9-6291-40a2-867b-8ff39a20b70f",
            "price": 99.99
        }
    ]
}
```


#### Get Product by ID

You can request all Products in the database.

`GET` `/products/:id`

|Attributes|Required|Description|
|----------|--------|-----------|
|id|Yes|A unique identifier of a Product.|


###### Example Request


```
curl -X GET http://0.0.0.0:8080/api/products/1
```

###### Example Response

```
{
    "data": {
        "_id": "1",
        "name": "Chefman 6-in-1 Espresso Machine Powerful 15-Bar Pump",
        "brand": "Chefman",
        "description": "This 6-in-1 coffee machine creates all of your favorite high-quality coffee beverages right in your kitchen. Enjoy single or double shots of espresso, cappuccinos, lattes, and more with the integrated frothing system. Ditch your old coffee pot, get the upgrade you’ve been craving.",
        "category": "Equipment",
        "imageUrl": "https://firebasestorage.googleapis.com/v0/b/ecommerce-3dffb.appspot.com/o/krups-intuition-preference.png?alt=media&token=5ba8dbf9-6291-40a2-867b-8ff39a20b70f",
        "price": 179.99
    }
}
```

#### Get Products by List of ID

You can request all Products in the database.

`POST` `/products/search`

|Attributes|Required|Description|
|----------|--------|-----------|
|data|Yes|A List of unique IDs of a Product.|


###### Example Request


```
curl -X POST http://0.0.0.0:8080/api/products
-d '{
"data": [1, 2]
}'
```

###### Example Response

```
{
    "data": [
        {
            "_id": "1",
            "name": "Chefman 6-in-1 Espresso Machine Powerful 15-Bar Pump",
            "brand": "Chefman",
            "description": "This 6-in-1 coffee machine creates all of your favorite high-quality coffee beverages right in your kitchen. Enjoy single or double shots of espresso, cappuccinos, lattes, and more with the integrated frothing system. Ditch your old coffee pot, get the upgrade you’ve been craving.",
            "category": "Equipment",
            "imageUrl": "https://firebasestorage.googleapis.com/v0/b/ecommerce-3dffb.appspot.com/o/krups-intuition-preference.png?alt=media&token=5ba8dbf9-6291-40a2-867b-8ff39a20b70f",
            "price": 179.99
        },
        {
            "_id": "2",
            "name": "Cimbali Power Espresso Machine",
            "brand": "Cimbali",
            "description": "This 6-in-1 coffee machine creates all of your favorite high-quality coffee beverages right in your kitchen. Enjoy single or double shots of espresso, cappuccinos, lattes, and more with the integrated frothing system. Ditch your old coffee pot, get the upgrade you’ve been craving.",
            "category": "Equipment",
            "imageUrl": "https://firebasestorage.googleapis.com/v0/b/ecommerce-3dffb.appspot.com/o/krups-intuition-preference.png?alt=media&token=5ba8dbf9-6291-40a2-867b-8ff39a20b70f",
            "price": 99.99
        }
    ]
}
```

## User

#### Register User

Register User sending correct Register Data. In the response JWT is given.

`POST` `/users/register`

|Attributes|Required|Description|
|----------|--------|-----------|
|first_name|Yes|First Name supplied by a Client. Value cannot be empty.|
|last_name|Yes|Last Name supplied by a Client. Value cannot be empty.|
|email|Yes|Email supplied by a Client. Value must be a properly formatted email.|
|password|Yes|Password supplied by a Client. Value has to be at least 6 chars long.|

###### Example Request


```
curl -X POST http://0.0.0.0:8080/api/users/register
-d '{
"first_name":"Larry",
"last_name": "Colton",
"email": "test@exampleapi.com",
"password": "Aeaa12"
}'
```

###### Example Response

```
{
    "message": "User created",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0ZXN0LWJhY2tlbmQiLCJ1c2VyQXV0aCI6IjMzODViMDVhZDFiNDdhOTBkMzgyYTEwMjU3NDczNWM4NzgyMzVlNmUiLCJpc3MiOiJ0ZXN0LWJhY2tlbmQifQ.BkBymhMhOTnrmjgKRrbCKlpwDkS_HEc45leveNGAdPQ"
}
```

#### Register User

Login User sending correct Login Data. In the response JWT is given.

`POST` `/users/login`

|Attributes|Required|Description|
|----------|--------|-----------|
|email|Yes|Email supplied by a Client. Value must be a properly formatted email.|
|password|Yes|Password supplied by a Client. Value has to be at least 6 chars long.|

###### Example Request


```
curl -X POST http://0.0.0.0:8080/api/users/login
-d '{
"email": "test@exampleapi.com",
"password": "Aeaa12"
}'
```

###### Example Response

```
{
    "message": "Logged In",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ0ZXN0LWJhY2tlbmQiLCJ1c2VyQXV0aCI6IjMzODViMDVhZDFiNDdhOTBkMzgyYTEwMjU3NDczNWM4NzgyMzVlNmUiLCJpc3MiOiJ0ZXN0LWJhY2tlbmQifQ.BkBymhMhOTnrmjgKRrbCKlpwDkS_HEc45leveNGAdPQ"
}
```

## Contact

- :mailbox: Kerubyte@gmail.com
