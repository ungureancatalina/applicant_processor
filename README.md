## About the Project

**Applicant Processor** is a Java application that processes a list of internship applicants from a CSV file and outputs a clean summary in JSON format. It was developed to automatically filter, score, and rank candidates based on a set of business rules.

The project includes full validation of input data, scoring logic with bonus/malus adjustments, and export of useful summaries such as top applicants and average scores. It follows a clean, modular architecture for maintainability and testing.

---

## Technologies Used

| Tool        | Purpose                           |
|-------------|------------------------------------|
| Java 21     | Core programming language          |
| Gradle      | Build automation and dependency    |
| JUnit 5     | Unit testing framework             |
| Jackson     | JSON serialization and parsing     |
| Log4j2      | Logging system                     |

---

## Features

- Read applicant data from `.csv`
- Validate fields: name, email, datetime, and score
- Skip invalid or duplicate entries
- Apply bonus/malus to score:
  - **+1** if submitted early (first day)
  - **–1** if submitted late (last day)
- Sort and extract:
  - **Top 3 applicants** (after score adjustment)
  - **Average score** of top 50% (unadjusted)
- Export results in **JSON format**
- Graceful logging for all skipped rows

---

## Project Structure

```
applicant_processor/
├── domain/          -> Applicant data model
├── validator/       -> Modular field validators
├── repository/      -> CSV reading + invalid logging
├── service/         -> Processing pipeline and scoring
├── service/util/    -> Sorting, average, bonus/malus logic
├── ui/              -> Entry point to run the full app
├── test/            -> Unit and integration tests
└── output/          -> (Generated) JSON results
```

---

## How to Run

### Requirements

- Java 17 or 21+
- Gradle

### Steps

1. Clone the repository:
```bash
git clone https://github.com/ungureancatalina/applicant_processor
cd applicant_processor
```

2. Build the project:
```bash
./gradlew clean build
```

3. Run the app:
```bash
java -jar build/libs/applicant-processor.jar path/to/input.csv
```

---

### Optional CLI Arguments

| Option     | Description                              |
|------------|------------------------------------------|
| `--out`    | Output JSON file                          |
| `--logout` | Summary log of valid/skipped applicants   |
| `--debug`  | Enable verbose logging                    |

**Example:**
```bash
java -jar applicant-processor.jar applicants.csv --out result.json --logout skipped.log --debug
```

---

## Example Output

```json
{
  "uniqueApplicants": 5,
  "topApplicants": [
    "Hoffman-Rus",
    "Hambare",
    "Ramos"
  ],
  "averageScore": 9.33
}
```

