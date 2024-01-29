curl -X POST http://localhost:8080/createBook -d "{\"id\":\"126\"}" -H "content-type: application/json"

curl -X POST http://localhost:8080/createBookWithAuthor -d "{\"id\":\"128\",\"authorId\":\"124\"}" -H "content-type: application/json"


curl -X POST http://localhost:8080/createAuthor -d "{\"id\":\"124\"}" -H "content-type: application/json"

curl http://localhost:8080/getBook/123 

curl http://localhost:8080/getAllBooks
curl http://localhost:8080/getAllAuthors


