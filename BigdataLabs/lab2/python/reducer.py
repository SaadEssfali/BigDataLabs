from operator import itemgetter
import sys

current_word = None
current_count = 0
word = None

# Lecture depuis l'entrée standard (STDIN)
for line in sys.stdin:
    line = line.strip()  # Supprimer les espaces en début et fin de ligne

    # Découper la ligne sur le caractère tabulation ('\t') fourni par mapper.py
    try:
        word, count = line.split('\t', 1)
        count = int(count)
    except ValueError:
        # Ignorer les lignes mal formatées
        continue

    # Si le mot courant est identique au précédent, on incrémente le compteur
    if current_word == word:
        current_count += count
    else:
        # Si on change de mot, on écrit le résultat précédent
        if current_word:
            print(f"{current_word}\t{current_count}")
        current_count = count
        current_word = word

# Afficher le dernier mot
if current_word == word:
    print(f"{current_word}\t{current_count}")