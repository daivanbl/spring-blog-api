# spring-blog-api

This application is build by SpringBoot.

## Development

Verify the deployment by navigating to your address (http://127.0.0.1:8080) in
your preferred browser or any API Testing Tools.
# API Documentation
### Blog

API for retrieving all Blogs from Database
```http
GET /api/getAllBlogs
```
| Query Param | Type | Description |
| :--- | :--- | :--- |
| `sort` | `string` | **Optional**. Default is sorting asc |
| `field` | `string` | **Optional**. Default is field id |

### Responses

```javascript
{
    "data": [
        {
            "id": int,
            "title": string,
            "body": string,
            "author": string
        }
    ],
    "httpStatus": string,
    "message": string
}
```

API for retrieving Blog by id
```http
GET /getBlogById/{id}
```

### Responses

```javascript
{
    "data": {
        "id": int,
        "title": string,
        "body": string,
        "author": string
    },
    "httpStatus": string,
    "message": string
}
```

API for retrieving Blogs with pagination
```http
GET /pagination/{offset}/{pageSize}
```

### Responses

```javascript
{
    "data": [
        {
            "id": int,
            "title": string,
            "body": string,
            "author": string
        }
    ],
    "httpStatus": string,
    "message": string
}
```

API for create a Blog and insert into Database
```http
POST /api/postBlog
```
| Body | Type | Description |
| :--- | :--- | :--- |
| `title` | `string` | **Required**|
| `body` | `string` | **Required**|
| `author` | `string` | **Required**|

### Responses

```javascript
{
    "data": {
        "id": int,
        "title": string,
        "body": string,
        "author": string
    },
    "httpStatus": string,
    "message": string
}
```

API for update a Blog content
```http
POST /api/updateBlogById/{id}
```
| Body | Type | Description |
| :--- | :--- | :--- |
| `title` | `string` | **Required**|
| `body` | `string` | **Required**|
| `author` | `string` | **Required**|

### Responses

```javascript
{
    "data": {
        "id": int,
        "title": string,
        "body": string,
        "author": string
    },
    "httpStatus": string,
    "message": string
}
```

API for delete a Blog
```http
DELETE /api/updateBlogById/{id}
```
### Responses

```javascript
{
    "data": {
        "id": int,
        "title": string,
        "body": string,
        "author": string
    },
    "httpStatus": string,
    "message": string
}
```

## Status Codes

Gophish returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 204 | `NO CONTENT` |
| 400 | `BAD REQUEST` |
| 403 | `FORBIDDEN` |
| 500 | `INTERNAL SERVER ERROR` |

