# coding-challenge

## Assumptions

* Deleting a loan will not remove investments
* There is only one investment and mortgage product type
* All amounts are stored in pennies to avoid rounding errors
* All interest made on loan goes back to investors (no cut for Landbay)
* Each investment made into a loan is unique
* Validation of fields is not required and users will put in sensible values for this challenge
* In memory database (non-production) is used when application starts up
* Step 3 says investments are valid for a day so we need to calculate interest per day for investment rather than monthly and return total over a specified period
* Assume that a year has 365 days for purpose of this challenge

## How to run

* Build using 
``
./mvnw clean package
`` 
* Run using:
``
java -jar target/coding-challenge-0.0.1-SNAPSHOT.jar
``

## Create a loan

Creates a loan that a lender can invest into

**URL** : `/api/loan`

**Method** : `POST`

**Data examples:**

```json
{
    "amount": 20000000,
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
    "amount": 20000000,
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
    "amount": 20000000,
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

loanId - id of loan to invest into
lenderId - id of lender that is making the investment

**Method** : `POST`

**Data examples:**

```json
{
    "amount": 100000,
    "loanId": 1,
    "lenderId": 1
}
```

### Success Response

**Code** : `200 OK`

**Content examples:**

```json
{
    "id": 1,
    "loan": {
      // loan object without investments
    }
}
```

## Get interest owed on investments over a time period

**URL** : `/api/investment/interest/{lenderId}?start={start}&end={end}`

lenderId - id of lender that is making the investment
start - start period to calculate interest
end - end period to calculate interest

**Method** : `GET`

**Data examples:**

```
/api/investment/interest/1?start=2018-10-21&end=2018-11-2018
```

### Success Response

**Code** : `200 OK`

total - interest owed over that time period

**Content examples:**

```json
{
    "total": 800
}
```
