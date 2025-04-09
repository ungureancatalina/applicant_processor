# 🌸 AXON Soft Internship — Applicant Statistics 🌸

Hi! I'm Ungurean Cătălina-Iuliana and this is my submission for the AXON Soft 2025 Internship Challenge.

I truly hope this project is close to what you're looking for, and honestly, I had fun building something that could actually help, not just sit in a folder and gather digital dust.

---

##  About the Project

This Java application processes a CSV file of internship applicants and generates a summary in JSON format.

It follows all the rules described in the challenge, including:
- Validations for name, email, datetime and score
- Ignoring invalid or duplicate rows
- Bonus (+1) if submitted on the first day
- Malus (-1) if submitted late on the last day
- Extracts the top 3 applicants (by adjusted score)
- Calculates average score (top half, unadjusted)

---

##  Output Example

```json
{
  "uniqueApplicants": 5,
  "topApplicants": [
    "Hambare",
    "Ramos",
    "Hoffman-Rus"
  ],
  "averageScore": 9.33
}
```

---

##  Technologies Used

| Tool        | Purpose                           |
|-------------|------------------------------------|
| **Java 21** | Core language                      |
| **Gradle**  | Build & dependency manager         |
| **JUnit 5** | Unit testing framework             |
| **Jackson** | JSON serialization                 |
| **Log4j2**  | Structured logging by level        |

---

##  Overview & Highlights

- Fully aligned with the technical & functional requirements  
- Clean, layered architecture following **SOLID** principles  
- Uses **KISS** and **DRY** consistently  
- Short, descriptive method names  
- Minimal and helpful inline comments  
- Modular code, easy to extend & test  
- Fully tested with **JUnit 5** (validators, repo, service, utils, processor)  
- All input edge cases handled with graceful logging (no crashes)

---

##  Project Structure

```
applicant.statistics
├── domain/          → Applicant model
├── repository/      → CSV input + error logging
├── service/         → Processing logic (with utilities)
├── service/util/    → Bonus/malus, sorting, average logic
├── validator/       → Modular field validators
├── ui/              → Orchestration of full logic
├── main/            → CLI entry point
└── test/            → Unit & integration tests
```

---

##  How to Run the App

### 1. Build the project:
```bash
./gradlew clean build
```

### 2. Run from terminal:
```bash
java -jar build/libs/applicant-statistics.jar path/to/input.csv
```

### 3. Optional CLI arguments:

| Option     | What it does                        |
|------------|-------------------------------------|
| `--out`    | Write JSON to output file           |
| `--logout` | Save a summary (total/valid/skipped)|
| `--debug`  | Enables detailed logging            |

**Example:**
```bash
java -jar app.jar applicants.csv --out results.json --logout summary.txt --debug
```

---

## How to Run the Tests

Run all tests with:
```bash
./gradlew test
```

Test report:
```
build/reports/tests/test/index.html
```

---

## Final Thoughts

This project was built with care, curiosity, and a strong focus on clean architecture and realistic functionality. Every feature aligns with the challenge requirements and the structure is ready for both feedback and future growth. It was genuinely fun to work on something practical. I hope you'll find that it reflects both attention to detail and enthusiasm for software development.

Thank you again for the opportunity to share my work!

**Warm regards,**

**Ungurean Cătălina-Iuliana** 🐞
