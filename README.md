# coding-challenge

## Assumptions

* Deleting a loan will not remove investments
* There is only one investment and mortgage product type
* All amounts are stored in pennies to avoid rounding errors
* All interest made on loan goes back to investors (no cut for Landbay)

## Create a loan

Creates a loan that a lender can invest into

**URL** : `/api/loan`

**Method** : `POST`

**Data examples:**

```json
{
    "amount": "20000000",
    "annualInterest": "3.4",
    "propertyAddress": {
      "street": "London Road",
      "number": 18,
      "city": "London",
      "postcode": "CR7 7PB"
    },
    "term": 25,
    "endDate": "2043-10-04"
}
```

### Success Response

**Code** : `200 OK`

**Content examples:**

```json
{
    "id": 1,
    "annualInterest": "3.4",
    "propertyAddress": {
      "street": "London Road",
      "number": 18,
      "city": "London",
      "postcode": "CR7 7PB"
    },
    "term": 25,
    "endDate": "2043-10-04"
}
```

## Get a loan and it's investments

**URL** : `/api/loan/{loanId}`

loanId - id of loan returned in created response

**Method** : `GET`

### Success Response

**Code** : `200 OK`

**Content examples:**

```json
{
    "id": 1,
    "amount": "20000000",
    "annualInterest": "3.4",
    "propertyAddress": {
      "street": "London Road",
      "number": 18,
      "city": "London",
      "postcode": "CR7 7PB"
    },
    "term": 25,
    "investments": [
      {
          "id": 1,
          "amount": "100000"
      },
      {
          "id": 2,
          "amount": "200000"
      }
    ],
    "endDate": "2043-10-04"
}
```

## Delete a loan

**URL** : `/api/loan/{loanId}`

loanId - id of loan to delete

**Method** : `DELETE`

### Success Response

**Code** : `204 No Content`

## Create investment into a loan

**URL** : `/api/investment/{loanId}`

loanId - id of loan to delete

**Method** : `POST`

**Data examples:**

```json
{
    "amount": "100000"
}
```

### Success Response

**Code** : `200 OK`

**Content examples:**

```json
{
    "id": 1,
    "amount": "100000"
}
```
