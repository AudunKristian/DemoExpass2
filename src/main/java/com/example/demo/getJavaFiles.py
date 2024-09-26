import os

def get_java_files_content(directory):
    java_files_content = {}
    
    # Traverse through all files in the given directory
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                with open(file_path, 'r', encoding='utf-8') as f:
                    java_files_content[file] = f.read()
    
    return java_files_content

# Get the directory where the Python script is located (same as Java files)
directory = os.path.dirname(os.path.abspath(__file__))

java_files_content = get_java_files_content(directory)

# Print all file names and their content
for file_name, content in java_files_content.items():
    print(f"File: {file_name}\n")
    print(content)
    print("\n" + "-" * 80 + "\n")

# If you want to save all the content to a single file, uncomment this part:
with open(os.path.join(directory, "all_java_files.txt"), "w", encoding="utf-8") as output_file:
    for file_name, content in java_files_content.items():
        output_file.write(f"File: {file_name}\n")
        output_file.write(content)
        output_file.write("\n" + "-" * 80 + "\n")
