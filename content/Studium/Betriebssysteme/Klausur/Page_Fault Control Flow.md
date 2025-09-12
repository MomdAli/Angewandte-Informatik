---
{"publish":true,"draft":true,"created":"2025-01-27T11:48:06.382+01:00","modified":"2025-09-12T21:25:01.464+02:00","published":"2025-09-12T21:25:01.464+02:00","tags":["excalidraw","Informatik"],"cssclasses":""}
---

==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠== You can decompress Drawing data with the command palette: 'Decompress current Excalidraw file'. For more info check in plugin settings under 'Saving'


# Excalidraw Data

## Text Elements
CPU ^5Ke2RH0o

RAM ^ke6KQTfu

Storage ^k88814qR

Page Table ^nHYN7DXD

TLB ^cJOO5JxQ

Page Fault Control Flow ^x42TxGNT

1. TLB Lookup ^cjoQkXj8

2. TLB Miss ^3eWzCJOv

Operating System ^HATqhcjN

3. Page not present:
Trap Instruction ^AvBK46Ux

4. Check storage,
whether the page exists. ^ZVzwEruZ

5. Put the page in ^z9hirPhG

6. Reset Page Table ^jq39kKIL

8. TLB Lookup ^VRq3uDTH

9. TLB Miss ^FOChlph4

7. Reinstruction ^9k7NDeGp

10. Lookup ^znbdEkGt

11. TLB Insert ^s9TvhpYi

12. Reinstruction ^eZPXzqle

13. TLB Lookup ^bVAZ17TQ

14. TLB Hit ^FbIbw78u

    1:   PFN = FindFreePhysicalPage()
    2:   if (PFN == -1)
    3:       PFN = EvictPage()
    4:   DiskRead(PTE.DiskAddr, PFN)
    5:   PTE.present = true
    6:   PTE.PFN = PFN
    7:   RetryInstruction() ^cIRWMOHw

Software Page Fault Control Flow ^vXqHJNzD

