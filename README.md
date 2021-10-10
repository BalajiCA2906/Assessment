# Assessment
Coffee Shop Assessment for 101 Digtal
http://localhost:9003/order/saveOrder
request
{
    "order": {
    "id":1044,
    "name":"cappuccino",
    "qnty":1,
    "price":3

},
"payment":{}

}
Response
{
    "order": {
        "id": 1044,
        "ownerId": 0,
        "customerId": 0,
        "name": "Test",
        "counter": 0,
        "queue": 0,
        "qnty": 1,
        "price": 200.0
    },
    "amount": 200.0,
    "message": "Success",
    "tansactionId": null
}
http://localhost:9003/payment/1044
Response
[
    {
        "paymentId": 1,
        "paymentStatus": "Fails",
        "transactionId": "b301e4cf-755f-4213-9dea-9163ea2d3fc3",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 2,
        "paymentStatus": "Fails",
        "transactionId": "f79135fe-1c29-4de8-a75a-ee6ab92c2b65",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 3,
        "paymentStatus": "Fails",
        "transactionId": "6ac80546-8b20-4ebf-9b03-bde0c09e0535",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 4,
        "paymentStatus": "Fails",
        "transactionId": "c2dc81b8-e0b2-4c4d-9e29-e4d12ec80132",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 5,
        "paymentStatus": "Fails",
        "transactionId": "67a27804-72c5-451b-880f-52267c1fe86e",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 6,
        "paymentStatus": "Fails",
        "transactionId": "df10a2a3-05ab-4fe1-80d4-dd6572fe37de",
        "orderId": 1044,
        "amount": 200.0
    },
    {
        "paymentId": 7,
        "paymentStatus": "Success",
        "transactionId": "eefacb01-96ac-4072-9668-9efe3cdf896b",
        "orderId": 1044,
        "amount": 200.0
    }
]
