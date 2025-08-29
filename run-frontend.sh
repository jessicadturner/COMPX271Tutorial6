#!/bin/bash

# Check if PATH_TO_FX is set
if [ -z "$PATH_TO_FX" ]; then
  echo "Error: PATH_TO_FX environment variable is not set."
  echo "Please set it to the path of your JavaFX SDK lib directory."
  exit 1
fi

# Navigate to the frontend source directory
cd frontend/src/main/java || exit

# Compile the JavaFX application
javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml com/booking/ui/MainUI.java

# Run the JavaFX application
java --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml com.booking.ui.MainUI
