# PC Store - Team 4

Εφαρμογή καταστήματος Η/Υ

Ενα συστημα για ενα ονλαιν καταστημα το οποιο ασχολειται με την πωληση ετοιμων υπολογιστων, κομματιων υπολογιστων, περιφερειακων κλπ. Στο καταστημα αυτο θα μπορει να μπαινει ενας
πελατης, να κανει λογαριασμο εφοσον το επιθυμει ή να συνεχισει ανωνυμα, θα μπορει να βρει ανα κατηγοριες ή να ψαξει τα προιοντα που θελει τα οποια θα μπαινουν στη συνεχεια σε ενα ονλαιν καλαθι. Στη συνεχεια ο πελατης επιλεγει τροπο πληρωμης, παραδοσης/παραλαβης κλπ. Στο συστημα θα μπορει να μπαινει και ενας υπαλληλος του καταστηματος με διαφορετικες αδειες,
οπως πχ η αφαιρεση ενος προιοντος απο τα διαθεσιμα προιοντα.

> Προσθέστε τα εξής:
> * Ο πελάτης θα έχει επιλογή αγοράς μεμονωμένων εξαρτημάτων/έτοιμων Η/Υ ή σύνθεσης και αγοράς υπολογιστή. Στην δεύτερη περίπτωση η εφαρμογή θα εξασφαλίζει ότι ο πελάτης έχει επιλέξει όλα τα απαιτούμενα εξαρτήματα καθώς και το ότι υπάρχει συμβατότητα μεταξύ αυτών
> * Οι υπάλληλοι του καταστήματος θα ενημερώνουν τον κατάλογο των προϊόντων με στοιχεία αποθέματος, τιμής και συμβατότητας μεταξύ των εξαρτημάτων. Η συμβατότητα θα αφορά κυρίως μητρικές πλακέτες και επιμέρους εξαρτήματα που προσαρμόζονται σε αυτές
> * Θα παράγονται αναφορές μηνιαίων εσόδων προς τον ιδιοκτήτη του καταστήματος

# R1: Περιγραφή του πεδίου προβλήματος.

#### Απαίτηση 1 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης

> Ο πελάτης μέσω της εφαρμογής μπορεί να δημιουργήσει τον προσωπικό του λογαριασμό, που περιέχει προσωπικά στοιχεία και προεραιτικά τα στοιχεία πληρωμής (πιστωτική κάρτα ή online wallet). Διαφορετικά μπορεί να συνεχίσει ανώνυμα, ως guest.

#### Απαίτηση 2 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης / Πληροφόρησης

> Κατά την είσοδο του πελάτη, εμφανίζονται οι κατηγορίες των προϊόντων και υπηρεσιών καθώς και μία μπάρα αναζήτησης. Εκεί μπορεί να αναζητήσει προϊόντα για τα οποία μπορεί να δει αναλυτικά τα χαρακτηριστικά τους, να τα προσθέσει στην "λίστα αγαπημένων" (wishlist) ή στο καλάθι αγοράς.

#### Απαίτηση 3 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης / Πληροφόρησης

> Ο πελάτης έχει την δυνατότητα αγοράς μεμονωμένων εξαρτημάτων, έτοιμων Η/Υ ή να δημιουργήσει την δικιά του σύνθεση. Σε αυτή την περίπτωση η εφαρμογή θα εξασφαλίζει ότι ο πελάτης έχει επιλέξει όλα τα απαιτούμενα εξαρτήματα καθώς και το ότι υπάρχει συμβατότητα μεταξύ αυτών.

#### Απαίτηση 4 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης

> Κατά την δημιουργία της παραγγελίας, ο πελάτης καλείται να συνδεθεί στον λογαριασμό του. Διαφορετικά, συμπληρώνει μια φόρμα με τα προσωπικά του στοιχεία. Επίσης το σύστημα του δίνει τη δυνατότητα να επιλέξει μέθοδο πληρωμής (Πληρωμή με κάρτα, Πληρωμή με Online Wallet, Πληρωμή μέσω τραπέζης, Αντικαταβολή) και τρόπο παραλαβής (παραλαβή από κατάστημα, παράδοση στο σπίτι).

#### Απαίτηση 5 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης

> Κατά την καταχώρηση της παραγγελίας, δημιουργείται το τιμολόγιο και προωθείται στην αποθήκη όπου ένας υπάλληλος ενημερώνει κατάλληλα το απόθεμα των προϊόντων.

#### Απαίτηση 6 - Τύπος: Επίτευξης, Κατηγορία: Απόκρισης

> Το σύστημα στέλνει στον πελάτη ενα e-mail, που περιλαμβάνει τα στοιχεία της παραγγελίας, το τιμολόγιο και έναν κωδικό για την παρακολούθηση της παραγγελίας καθώς και έναν σύνδεσμο με την ιστοσελίδα της συνεργαζόμενης εταιρίας κούριερ.

#### Απαίτηση 7 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης / Πληροφόρησης

> Το σύστημα είναι υπεύθυνο να κρατάει ιστορικό των μηνιαίων εσόδων και να δημιουργεί τις αντίστοιχες αναφορές προς τον ιδιοκτήτη του καταστήματος.
> 
#### Απαίτηση 8 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης

> Κάθε υπάλληλος έχει τον δικό του λογαριασμό, με διαφορετική άδεια από τους πελάτες, όπου μπορεί να συνδεθεί στο σύστημα και έχει πρόσβαση σε επιπλέον ένεργειες, όπως πρόσβαση στο αρχείο παραγγελιών, προβολή στατιστικών, επισκόπηση και ενημέρωση παραγγελιών, επικοινωνία με τον πελάτη.

#### Απαίτηση 9 - Τύπος: Επίτευξης, Κατηγορία: Ικανοποίησης

> Οι υπάλληλοι του καταστήματος πρέπει να ενημερώνουν τον κατάλογο των προϊόντων με τα στοιχεία αποθέματος, την τιμή και τη συμβατότητα μεταξύ των εξαρτημάτων.

Μοντέλο Περιπτώσεων Χρήσης: [<img src="https://gitlab.com/softeng-2019-20/pc-store/-/blob/master/use-case-model.md">](https://gitlab.com/softeng-2019-20/pc-store/-/blob/master/use-case-model.md)