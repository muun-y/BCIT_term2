#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef enum {
    TYPE_INT,
    TYPE_CHAR,
    TYPE_DOUBLE,
    TYPE_FLOAT
} DataType;

typedef struct Node {
    void *key;
    DataType keyType;
    void *value;
    DataType valueType;
    size_t keySize;
    size_t valueSize;
    struct Node *next;
    struct Node *prev;
} Node;

// Function prototypes
Node* createNode(void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize); 
void insertNode(Node **head, void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize); 
void printKeys(Node *head);
void printValues(Node *head);
void saveToFile(Node *head, const char *filename);
Node* restoreFromFile(const char *filename);
void freeList(Node *head);
void *getInput(size_t *size, DataType *type, const char *dataType); 

int main() {
    Node *head = NULL;
    int choice;
    char filename[100];
    DataType keyType, valueType;

    while(1) {
        printf("1) Print Keys\n2) Print Values\n3) Insert KV Pair\n4) Save\n5) Restore\n6) Exit\n");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printKeys(head);
                break;
            case 2:
                printValues(head);
                break;
            case 3: {
                size_t keySize, valueSize;
                void *key, *value;

                key = getInput(&keySize, &keyType, "Key"); 
                value = getInput(&valueSize, &valueType, "Value"); 

                insertNode(&head, key, keyType, keySize, value, valueType, valueSize);

                free(key);
                free(value);
                break;
            }
            case 4:
                printf("Type the file name: ");
                scanf("%s", filename);
                saveToFile(head, filename);
                break;
            case 5:
                printf("Type the file name: ");
                scanf("%s", filename);
                freeList(head);
                head = restoreFromFile(filename);
                break;
            case 6:
                freeList(head);
                return 0;
            default:
                printf("\n");
        }
    }
    return 0;
}

void *getInput(size_t *size, DataType *type, const char *dataType) {

    void *data;
    char input[256]; 

    printf("Insert %s: ", dataType);
    scanf("%s", input); 

    printf("%s data size: ", dataType);
    scanf("%zu", size);

    switch (*size) {
        case 1:  // char
            *type = TYPE_CHAR;
            data = malloc(sizeof(char));
            *(char *)data = input[0];
            break;
        case 4:  // int or float
            if (strchr(input, '.') != NULL) { 
                *type = TYPE_FLOAT;
                data = malloc(sizeof(float));
                *(float *)data = atof(input);
            } else { 
                *type = TYPE_INT;
                data = malloc(sizeof(int));
                *(int *)data = atoi(input);
            }
            break;
        case 8:  // double
            *type = TYPE_DOUBLE;
            data = malloc(sizeof(double));
            *(double *)data = atof(input);
            break;
        default:
            printf("Invalid data size.\n");
            exit(EXIT_FAILURE);
    }

    return data;
}

Node* createNode(void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize) {
    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->key = malloc(keySize);
    newNode->value = malloc(valueSize);
    memcpy(newNode->key, key, keySize);
    memcpy(newNode->value, value, valueSize);
    newNode->keyType = keyType;
    newNode->valueType = valueType;
    newNode->keySize = keySize;
    newNode->valueSize = valueSize;
    newNode->next = newNode->prev = NULL;
    return newNode;
}

void insertNode(Node **head, void *key, DataType keyType, size_t keySize, void *value, DataType valueType, size_t valueSize) {
    Node *newNode = createNode(key, keyType, keySize, value, valueType, valueSize);
    if (*head == NULL) {
        *head = newNode;
    } else {
        newNode->next = *head;
        (*head)->prev = newNode;
        *head = newNode;
    }
}

void printKeys(Node *head) {
    Node *current = head;
    while (current != NULL) {
        switch (current->keyType) {
            case TYPE_INT:
                printf("%d\n", *(int *)(current->key));
                break;
            case TYPE_CHAR:
                printf("%c\n", *(char *)(current->key));
                break;
            case TYPE_DOUBLE:
                printf("%f\n", *(double *)(current->key));
                break;
            case TYPE_FLOAT:
                printf("%f\n", *(float *)(current->key));
                break;
        }
        current = current->next;
    }
}

void printValues(Node *head) {
    Node *current = head;
    while (current != NULL) {
        switch (current->valueType) {
            case TYPE_INT:
                printf("%d\n", *(int *)(current->value));
                break;
            case TYPE_CHAR:
                printf("%c\n", *(char *)(current->value));
                break;
            case TYPE_DOUBLE:
                printf("%lf\n", *(double *)(current->value)); 
                break;
            case TYPE_FLOAT:
                printf("%f\n", *(float *)(current->value)); 
                break;
        }
        current = current->next;
    }
}

void saveToFile(Node *head, const char *filename) {
    FILE *file = fopen(filename, "wb");
    if (!file) {
        perror("Unable to open file");
        return;
    }

    Node *current = head;
    while (current != NULL) {
        fwrite(&current->keyType, sizeof(current->keyType), 1, file);
        fwrite(&current->keySize, sizeof(current->keySize), 1, file);
        fwrite(current->key, current->keySize, 1, file);
        fwrite(&current->valueType, sizeof(current->valueType), 1, file);
        fwrite(&current->valueSize, sizeof(current->valueSize), 1, file);
        fwrite(current->value, current->valueSize, 1, file);
        current = current->next;
    }
    fclose(file);
}


Node* restoreFromFile(const char *filename) {
    FILE *file = fopen(filename, "rb");
    if (!file) {
        perror("Unable to open file");
        return NULL;
    }

    Node *head = NULL;
    DataType keyType, valueType;
    size_t keySize, valueSize;

    while (fread(&keyType, sizeof(keyType), 1, file) == 1) {
        fread(&keySize, sizeof(keySize), 1, file);
        void *key = malloc(keySize);
        fread(key, keySize, 1, file);

        fread(&valueType, sizeof(valueType), 1, file);
        fread(&valueSize, sizeof(valueSize), 1, file);
        void *value = malloc(valueSize);
        fread(value, valueSize, 1, file);

        insertNode(&head, key, keyType, keySize, value, valueType, valueSize);

        free(key);
        free(value);
    }
    fclose(file);
    return head;
}

void freeList(Node *head) {
    Node *current = head;
    while (current != NULL) {
        Node *temp = current;
        current = current->next;
        free(temp->key);
        free(temp->value);
        free(temp);
    }
}

