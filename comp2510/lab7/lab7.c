#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct{
   char lastName[30];
   char firstName[30]; 
   char studentNumber[9]; 
   int midtermGrade; 
   int finalGrade; 
} Student; 

//function to calculate the average of the grade
int calculateAverage(Student s){
   return (s.midtermGrade + s.finalGrade) / 2; 
}

int compareStudents(const void *a, const void *b){
   Student *studentA = (Student *)a; 
   Student *studentB = (Student *)b; 
   
   //compare last name
   int lastNameComp = strcmp(studentA -> lastName, studentB -> lastName); 
   if(lastNameComp != 0) return lastNameComp; 
   
   // Compare first names
    int firstNameComp = strcmp(studentA->firstName, studentB->firstName);
    if (firstNameComp != 0) return firstNameComp;

    // Compare student numbers
    int studentNumberComp = strcmp(studentA->studentNumber, studentB->studentNumber);
    if (studentNumberComp != 0) return studentNumberComp;

    // Compare midterm grades
    if (studentA->midtermGrade != studentB->midtermGrade)
        return studentA->midtermGrade - studentB->midtermGrade;

    // Compare final grades
    return studentA->finalGrade - studentB->finalGrade;
}
          
int main(int argc, char *argv[]) {
    // Check for correct number of arguments
    if (argc != 4) {
        fprintf(stderr, "Usage: %s <input file> <output file> <option>\n", argv[0]);
        return 1;
    }

    FILE *inputFile, *outputFile;
    int option;
    int numberOfStudents = 0;
    Student students[100]; // Assuming a max of 100 students for the example
    
    // Open input file
    inputFile = fopen(argv[1], "r");
    if (inputFile == NULL) {
        fprintf(stderr, "Error opening input file.\n");
        return 1;
    }
    
    // Read students from file
    while(fscanf(inputFile, "%s %s %s %d %d", 
                 students[numberOfStudents].firstName, 
                 students[numberOfStudents].lastName, 
                 students[numberOfStudents].studentNumber, 
                 &students[numberOfStudents].midtermGrade, 
                 &students[numberOfStudents].finalGrade) != EOF) {
        numberOfStudents++;
    }
    fclose(inputFile);
    
    // Sort students
    qsort(students, numberOfStudents, sizeof(Student), compareStudents);
    
    // Open output file
    outputFile = fopen(argv[2], "w");
    if (outputFile == NULL) {
        fprintf(stderr, "Error opening output file.\n");
        return 1;
    }
    
    // Convert option from string to int
    option = atoi(argv[3]);
    
    // Write sorted students to output file based on the option
for (int i = 0; i < numberOfStudents; i++) {
    int average = calculateAverage(students[i]);

    if ((option == 1 && average > 90) ||
        (option == 2 && average > 80 && average <= 90) ||
        (option == 3 && average > 70 && average <= 80) ||
        (option == 4 && average > 60 && average <= 70) ||
        (option == 5 && average <= 60)) {

        fprintf(outputFile, "%s %s %s %d %d\n",
                students[i].firstName, students[i].lastName,students[i].studentNumber,
                students[i].midtermGrade,
                students[i].finalGrade);
    }
}
    fclose(outputFile);
    
    return 0;
}


