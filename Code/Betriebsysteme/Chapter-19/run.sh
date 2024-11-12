#!/bin/bash
> results.txt

for pages in 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192; do
    ./tlb $pages 100000 >> results1.txt
done

for pages in 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192; do
    ./tlb $pages 100000 >> results2.txt
done

for pages in 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192; do
    ./tlb $pages 100000 >> results3.txt
done

for pages in 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192; do
    ./tlb $pages 100000 >> results4.txt
done