# rest_api_exam_m2

#### Account with role USER can only operate on the following routes (even though some routes'll return status code 200 they won't make changes on the DB):
- "/api/v1/stores/find",
- "/api/v1/sellers/find",
- "/api/v1/sellers/update",
- "/api/v1/tracking/new",
- "/api/v1/tracking/find",
- "/api/v1/selling/new",
- "/api/v1/products/new",
- "/api/v1/products/find",
- "/api/v1/products/update",
- "/api/v1/categories/new",
- "/api/v1/categories/find",
- "/api/v1/supplies/new",
- "/api/v1/supplies/update",
- "/api/v1/supplies/find"