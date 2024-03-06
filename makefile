# Compiler and execution settings
JAVAC = javac
JAVA = java
SRC_DIR = .
OUT_DIR = .

# Source files
SOURCES := $(shell find $(SRC_DIR) -name '*.java')

# Class files
CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

# Main class
MAIN_CLASS = App

# Default target
.DEFAULT_GOAL := all

# Rule to compile .java files to .class files
$(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $<

# Rule to build the project
all: $(CLASSES)

# Rule to run the project
run:
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS)

# Rule to clean generated files
clean:
	rm -rf $(OUT_DIR)

.PHONY: all run clean
