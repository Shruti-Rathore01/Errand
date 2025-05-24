# Errand Service API Endpoints

## Authentication Endpoints
| Method | Endpoint | Description | Authentication Required |
|--------|----------|-------------|------------------------|
| POST | `/auth/login` | Login and get JWT token | No |

## User Management Endpoints
| Method | Endpoint | Description | Authentication Required |
|--------|----------|-------------|------------------------|
| POST | `/users/register` | Register a new user | No |
| GET | `/users` | Get list of all users | Yes |
| GET | `/users/{id}` | Get user details by ID | Yes |
| PUT | `/users/{id}` | Update user information | Yes |
| DELETE | `/users/{id}` | Delete a user | Yes |

## Errand Management Endpoints
| Method | Endpoint | Description | Authentication Required |
|--------|----------|-------------|------------------------|
| POST | `/errands` | Create a new errand | Yes |
| GET | `/errands` | Get list of all errands | Yes |
| GET | `/errands/{id}` | Get errand details by ID | Yes |
| PUT | `/errands/{id}` | Update errand information | Yes |
| DELETE | `/errands/{id}` | Delete an errand | Yes |
| GET | `/errands/match/{userId}` | Get errands matching user's skills | Yes |
| PUT | `/errands/accept/{userId}/{errandId}` | Accept an errand | Yes |
| PUT | `/errands/complete/{errandId}` | Mark errand as completed | Yes |

## Feedback Endpoints
| Method | Endpoint | Description | Authentication Required |
|--------|----------|-------------|------------------------|
| POST | `/feedback/submit/{errandId}/{userId}` | Submit feedback for completed errand | Yes |

## Request/Response Examples

### Authentication
```json
// POST /auth/login
Request:
{
    "email": "user@example.com",
    "password": "password123"
}

Response:
{
    "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### User Registration
```json
// POST /users/register
Request:
{
    "name": "User Name",
    "email": "user@example.com",
    "password": "password123",
    "skill": "COOKING"
}
```

### Create Errand
```json
// POST /errands
Request:
{
    "title": "Cooking Service",
    "description": "Need cooking service for event",
    "requiredSkills": ["COOKING"],
    "status": "PENDING"
}
```

### Submit Feedback
```json
// POST /feedback/submit/{errandId}/{userId}
Request:
{
    "feedbackText": "Excellent service",
    "rating": 5
}
```

## Important Notes

### Available Skills
- CLEANING
- PAINTING
- DELIVERY
- GARDENING
- COOKING
- PLUMBING
- ELECTRICAL
- TUTORING

### Errand Status Types
- PENDING
- IN_PROGRESS
- COMPLETED

### Authentication
- For authenticated endpoints, include the JWT token in the Authorization header:
  ```
  Authorization: Bearer <your_jwt_token>
  ```
- Token can be obtained from the `/auth/login` endpoint
- Token expiration: 10 hours

### Validation Rules
- Email must be unique and valid format
- Password minimum length: 6 characters
- Feedback rating range: 1 to 5
- User name length: 2 to 50 characters 