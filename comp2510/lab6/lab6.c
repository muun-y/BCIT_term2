#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char firstName[100];
    char lastName[100];
    float gpa;
} DomesticStudent;

typedef struct {
    char firstName[100];
    char lastName[100];
    float gpa;
    int toefl;
} InternationalStudent;

typedef struct {
    int type;  // 1 for Domestic, 2 for International
    union {
        DomesticStudent d;
        InternationalStudent i;
    } student;
} Student;

void parseLine(char *line, Student *student, FILE *outputFile, int *errorOccurred) {
    char firstName[100];
    char lastName[100];
    float gpa;
    char status;
    int toefl;
    int fieldsRead = sscanf(line, "%s %s %f %c %d", firstName, lastName, &gpa, &status, &toefl);

    if (fieldsRead < 3) {
        fprintf(stderr, "Error: Not enough fields or incorrect format in line: %s", line);
        *errorOccurred = 1;
        return;
    }

    if (gpa < 0.0 || gpa > 4.3) {
        fprintf(stderr, "Error: GPA out of range in line: %s", line);
        *errorOccurred = 1;
        return;
    }

    if (status == 'I' && (fieldsRead == 5)) {
        if (toefl < 0 || toefl > 120) {
            fprintf(stderr, "Error: TOEFL score out of range in line: %s", line);
            *errorOccurred = 1;
            return;
        }
        student->type = 2;
        strcpy(student->student.i.firstName, firstName);
        strcpy(student->student.i.lastName, lastName);
        student->student.i.gpa = gpa;
        student->student.i.toefl = toefl;
    } else if (status == 'D' && (fieldsRead == 4 || fieldsRead == 3)) {
        student->type = 1;
        strcpy(student->student.d.firstName, firstName);
        strcpy(student->student.d.lastName, lastName);
        student->student.d.gpa = gpa;
    } else {
        fprintf(stderr, "Error: Invalid student status in line: %s", line);
        *errorOccurred = 1;
        return;
    }
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <input file> <output file> <option>\n", argv[0]);
        return 1;
    }

    FILE *inputFile = fopen(argv[1], "r");
    FILE *outputFile = fopen(argv[2], "w");
    int option = atoi(argv[3]);
    int errorOccurred = 0;

    if (!inputFile || !outputFile) {
        fprintf(stderr, "Error: Cannot open file.\n");
        if (inputFile) fclose(inputFile);
        if (outputFile) fclose(outputFile);
        return 1;
    }

    char line[256];
    Student student;

    while (fgets(line, sizeof(line), inputFile)) {
        memset(&student, 0, sizeof(student)); // Reset the student structure
        parseLine(line, &student, outputFile, &errorOccurred);

        if (errorOccurred) {
            // If an error occurred, skip writing to the file and continue with the next line
            errorOccurred = 0; // Reset the error flag
            continue; // Skip the rest of this loop iteration
        }

        // Check the student's GPA and type, then write to the output file based on the option
        if (student.student.d.gpa > 3.9) {
            if ((option == 1 && student.type == 1) || // Option 1: Domestic students only
                (option == 2 && student.type == 2 && student.student.i.toefl >= 70) || // Option 2: International students with TOEFL score >= 70
                (option == 3)) { // Option 3: All students
                // Write the student's data to the output file
                if (student.type == 1) {
                    fprintf(outputFile, "%s %s %.3f D\n",
                            student.student.d.firstName,
                            student.student.d.lastName,
                            student.student.d.gpa);
                } else if (student.type == 2) {
                    fprintf(outputFile, "%s %s %.3f I %d\n",
                            student.student.i.firstName,
                            student.student.i.lastName,
                            student.student.i.gpa,
                            student.student.i.toefl);
                }
            }
        }
    }

    fclose(inputFile);
    fclose(outputFile);

    return 0;
}
