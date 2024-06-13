# Super Health Inc., API
API for Super Health Inc that stores patient and encounter data. This database currently accept the following methods: GET, POST, PUT, DELETE.

## Getting Started

### Start the Server

1. Clone this project locally.
1. CD into the root folder
1. Open this project up in IntelliJ
1. Right click AppRunner, and select "Run 'AppRunner.main()'"


### Connections

By default, this service starts up on port 8085 and accepts cross-origin requests from `*`.

### Dependencies

#### JDK

You must have a JDK installed on your machine.

#### Postgres

This server requires that you have Postgres installed and running on the default Postgres port of 5432. It requires that you have a database created on the server with the name of `postgres`
- Your username should be `postgres`
- Your password should be `root`

## Postman Collection

A link to the postman collection is provided containing examples of valid and invalid requests for each endpoint.

Link: [Health Collection](https://www.getpostman.com/collections/c03e88fd69f336909d46)

To import this collection into Postman, click the "Import" button in the left navigation menu, click "Link" in the modal that opens, paste the above link into the box and click "Continue". 


## Swagger Link

Here is the link to the swagger documentation: [Swagger Health API Documentation](http://localhost:8085/swagger-ui/#/)

## Request Examples

<details><summary>GET 

`/patients`

 (Gets a list of all patients in the database)</summary>

```json
[
    {
        "id": 1,
        "firstName": "Luz",
        "lastName": "Lopez",
        "ssn": "123-13-1234",
        "email": "lmarcano@catalyte.io",
        "age": 21,
        "height": 60,
        "weight": 105,
        "insurance": "Medicare",
        "gender": "Female",
        "street": "123 St",
        "city": "Orange",
        "state": "NJ",
        "postal": "12345"
    },
    {
        "id": 2,
        "firstName": "Kiwi",
        "lastName": "Mango",
        "ssn": "123-13-1200",
        "email": "kiwi@gmail.com",
        "age": 22,
        "height": 61,
        "weight": 95,
        "insurance": "Medicare",
        "gender": "Male",
        "street": "123 St",
        "city": "Orange",
        "state": "AL",
        "postal": "12345"
    },
    {
        "id": 3,
        "firstName": "Matcha",
        "lastName": "Tea",
        "ssn": "123-13-1234",
        "email": "greentea@aaol.com",
        "age": 46,
        "height": 80,
        "weight": 215,
        "insurance": "Medicare",
        "gender": "Other",
        "street": "123 St",
        "city": "Orange",
        "state": "MD",
        "postal": "12345"
    }
]


```

</details>

<details><summary>GET

 `/patients/{id}`
 
   (Gets patient of indicated ID in the database. For this example, we'll be using ID of 1.)</summary>

```json
{
    "id": 1,
    "firstName": "Luz",
    "lastName": "Lopez",
    "ssn": "123-13-1234",
    "email": "lmarcano@catalyte.io",
    "age": 21,
    "height": 60,
    "weight": 105,
    "insurance": "Medicare",
    "gender": "Female",
    "street": "123 St",
    "city": "Orange",
    "state": "NJ",
    "postal": "12345"
}


```

</details>

<details><summary>GET 

`/patients/{id}/encounters`

 (Gets a list of all encounters for indicated patient in the database. For this example, we'll be using ID of 1.)</summary>

```json
[
    {
        "id": 1,
        "patientId": 1,
        "notes": "Notes",
        "visitCode": "H7J 8W2",
        "provider": "Ames Hospital",
        "billingCode": "123.456.789-22",
        "icd10": "Z10",
        "totalCost": 120.35,
        "copay": 50.00,
        "chiefComplaint": "Broken leg",
        "pulse": null,
        "systolic": null,
        "diastolic": null,
        "date": "2022-10-23"
    },
    {
        "id": 2,
        "patientId": 1,
        "notes": "Notes",
        "visitCode": "H7J 8W2",
        "provider": "Ames Hospital",
        "billingCode": "123.456.789-22",
        "icd10": "Z10",
        "totalCost": 120.35,
        "copay": 50.00,
        "chiefComplaint": "Broken leg",
        "pulse": null,
        "systolic": null,
        "diastolic": null,
        "date": "2022-10-23"
    }
]


```

</details>

<details><summary>GET 

`/patients/{id}/encounters/{encounterId}`

 (Gets encounter of indicated ID for indicated patient in the database. For this example, we'll be using encounter id of 1 and patient id of 1.)</summary>

```json
{
    "id": 1,
    "patientId": 1,
    "notes": "Notes",
    "visitCode": "H7J 8W2",
    "provider": "Ames Hospital",
    "billingCode": "123.456.789-22",
    "icd10": "Z10",
    "totalCost": 120.35,
    "copay": 50.00,
    "chiefComplaint": "Broken leg",
    "pulse": null,
    "systolic": null,
    "diastolic": null,
    "date": "2022-10-23"
}


```

</details>

## Testing

Once IntelliJ is open, navigate to the test folder and you will see Unit and Integration tests for the patient and encounter entities.

To run any of these files with coverage:
1. Open up the test folder you want to run tests for.
1. Right click on the test file and click `Run 'Test' with Coverage`. Test being the file you want to run tests for.


## How to Lint
```
Ctrl + Alt + L
```

