TLB = tlb

tlb: tlb.c
	$(CC) -O0 -D_GNU_SOURCE -o $@ $< -Wall

clean:
	rm -rf $(TLB)