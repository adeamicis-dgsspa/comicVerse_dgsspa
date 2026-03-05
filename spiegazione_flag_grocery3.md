# Spiegazione risoluzione challenge

## Obiettivo
Decifrare il contenuto di `grocery3.txt` partendo dagli indizi:
- script `secure_encryptor.py`
- foglio `Lista_Spesa.ods`
- hint: la chiave esadecimale e nascosta nella lista della spesa, ma i conti non tornano.

## 1) Analisi dello script di cifratura
Nel file `secure_encryptor.py` la funzione usata e:

```python
def secure_algorithm(data, key):
    return bytes([data[i] ^ key[i % len(key)] for i in range(len(data))])
```

Quindi e una cifratura XOR a chiave ripetuta.
Vincolo importante: la chiave deve essere lunga **6 byte** (12 caratteri hex).

Conclusione: per decifrare basta rifare XOR tra ciphertext e chiave da 6 byte.

## 2) Dato cifrato
`grocery3.txt` contiene:

```text
c7dea2b39e9091c0aea18997fed7b1a6d584fed4aabd9dc5c5ede2f598
```

## 3) Ispezione del file ODS
`Lista_Spesa.ods` e un archivio ZIP. Aprendo `content.xml` si vede che:
- ci sono colonne ripetute `Prodotto, Qty, Prezzo, TOTALE, HEX`
- il `TOTALE` e calcolato con formula tipo `=[Qty]+[Prezzo]`

Questo e l'indizio "i conti non tornano": il totale corretto dovrebbe essere `Qty * Prezzo`, non somma.

## 4) Ricerca della chiave
Dato che servono 6 byte, il pattern naturale e prendere 6 valori numerici consecutivi da una riga (una per ciascun blocco prodotto).

Provando le righe candidate, la riga con **ID 10** nei campi **Prezzo** contiene:

- 161, 178, 195, 212, 229, 246

Convertiti in esadecimale:

- 161 -> `a1`
- 178 -> `b2`
- 195 -> `c3`
- 212 -> `d4`
- 229 -> `e5`
- 246 -> `f6`

Chiave risultante:

```text
a1b2c3d4e5f6
```

Nota: se si prendono i valori `TOTALE` della stessa riga si ottiene la sequenza +1 (`a2b3c4d5e6f7`), che non produce la flag valida. Questo conferma l'hint sul fatto che i conti sono sbagliati.

## 5) Decifratura finale
Applicando XOR del ciphertext con chiave ripetuta `a1b2c3d4e5f6`, il plaintext diventa:

```text
flag{f0rmula_err0r_fiix3d_!!}
```

## Flag

```text
flag{f0rmula_err0r_fiix3d_!!}
```
