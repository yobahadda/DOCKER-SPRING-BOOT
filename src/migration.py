import mysql.connector
import csv
import random
# Database configuration
db_config = {
    'host': 'localhost',  # Change this to your MySQL host
    'user': 'root',  # Change this to your MySQL username
    'password': 'root',  # Change this to your MySQL password
    'database': 'testspring',  # Change this to your database name
}

def fill_table_from_csv(file_path):
    try:
        # Connect to the database
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor()

        # Open the CSV file
        with open(file_path, 'r') as csvfile:
            csvreader = csv.reader(csvfile)

            # Skip the header row
            next(csvreader)

            # Insert data into the table
            for row in csvreader:
                # Prepare data for the `etudiant` table
                id_value = int(row[0]) if row[0].isdigit() else None
                image_url = row[7]
                nom = row[4]
                prenom = row[3]
                filiere_id = random.randint(1, 4)

                try:
                    cursor.execute(
                        """
                        INSERT INTO etudiant (id, image_url, nom, prenom, filiere_id)
                        VALUES (%s, %s, %s, %s, %s)
                        """,
                        (id_value, image_url, nom, prenom, filiere_id)
                    )
                except mysql.connector.Error as insert_err:
                    print(f"Error inserting row {row}: {insert_err}")

        # Commit the transaction
        conn.commit()
        print("Data inserted successfully.")

    except mysql.connector.Error as err:
        print(f"Error: {err}")

    finally:
        # Close the connection
        if conn.is_connected():
            cursor.close()
            conn.close()

# Path to your CSV file
file_path = 'users_table.csv'  # Update the path if necessary

# Fill the table from the CSV
fill_table_from_csv(file_path)
