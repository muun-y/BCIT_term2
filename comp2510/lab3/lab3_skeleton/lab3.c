#include <stdio.h>
#include <stdlib.h>

// Function to rotate a 2D array by a given angle (in degrees)
void rotateArray(char **arr, int n, int rows, int cols) {
    // Your implementation here
    
    int temp[30][30];
    
    n = (n % 360 + 360) % 360; 
    
    switch (n % 360) {
    case 90:
    case -270:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = arr[rows - j - 1][i]; 
            }
        }
        break;

    case 180:
    case -180:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = arr[rows - i - 1][rows - j - 1]; 
            }
        }
        break;

    case 270:
    case -90:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                temp[i][j] = arr[j][i]; 
            }
        }
        break;

    default:
    
    for(int i = 0; i < rows;  i++){
        for(int j = 0; j <rows; j++){
            temp[i][j] = arr[i][j]; 
        }
    }
        break;
}

for (int i = 0; i < rows; i++) {
    for (int j = 0; j < rows; j++) {
        arr[i][j] = temp[i][j];
    }
}
}

// Function to zoom (expand or shrink) a 2D array by a given factor
void zoomArray(char **arr, double zoomFactor, int *rows, int *cols) {
    int i, j;
    int newRows = (int)(*rows * zoomFactor);
    int newCols = (int)(*cols * zoomFactor);
    char temp[30][30];

    for (i = 0; i < newRows; i++) {
        for (j = 0; j < newCols; j++) {
            int sourceRow = (int)(i / zoomFactor);
            int sourceCol = (int)(j / zoomFactor);
            temp[i][j] = arr[sourceRow][sourceCol];
        }
    }

    *rows = newRows;
    *cols = newCols;

    for (i = 0; i < newRows; i++) {
        for (j = 0; j < newCols; j++) {
            arr[i][j] = temp[i][j];
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("Usage: %s <input_file> <angle_degrees> <zoom_factor>\n", argv[0]);
        return 1;
    }

    char *inputFileName = argv[1];
    int angle = atoi(argv[2]);
    double zoomFactor = atof(argv[3]); // Use atof to convert the zoom factor to a double

    // Initialize a 2D array of size 30x30 as a pointer-to-pointer
    char **arr = (char **)malloc(30 * sizeof(char *));
    for (int i = 0; i < 30; i++) {
        arr[i] = (char *)malloc(30 * sizeof(char));
    }

    // Open the input file for reading
    FILE *file = fopen(inputFileName, "r");
    if (file == NULL) {
        printf("Failed to open the input file.\n");
        return 1;
    }

    // Read the input array from the specified file and populate arr
    int rows = 0;
    int cols = 0;
    char c;

    while (rows < 30 && cols < 30 && fscanf(file, "%c", &arr[rows][cols]) == 1) {
        if (arr[rows][cols] == '\n') {
          rows++;
          cols = 0;
        } else {
          cols++;
        }
    }
    cols = rows;
    
    // Close the input file
    fclose(file);

    // Print the array
    printf("Input Array:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    
    // Call rotateArray or zoomArray based on the angle and zoomFactor
    // For example:
    rotateArray(arr, angle, rows, cols);
    zoomArray(arr, zoomFactor, &rows, &cols);

    // Print the array
    printf("Output %dx%d Array:\n", rows, cols);
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          printf("%c ", arr[i][j]);
        }
        printf("\n");
    }

    // Free dynamically allocated memory when done
    for (int i = 0; i < 30; i++) {
        free(arr[i]);
    }
    free(arr);

    return 0;
}
