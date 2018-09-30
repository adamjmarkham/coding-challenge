# coding-challenge

## Create a loan

Creates a loan that a lender can invest into

**URL** : `/api/loan`

**Method** : `POST`

**Data examples**

```json
{
    "amount": "20000000",
    "description": "loan description",
    "annualInterest": "3.4",
    "propertyAddress": {
      "street": "London Road",
      "number": 18,
      "city": "London",
      "postcode": "CR7 7PB"
    }
}
```

### Success Response

**Code** : `200 OK`

**Content examples**

```json
{
    "id": 1,
    "amount": "20000000",
    "description": "loan description",
    "annualInterest": "3.4",
    "propertyAddress": {
      "street": "London Road",
      "number": 18,
      "city": "London",
      "postcode": "CR7 7PB"
    }
}
```
