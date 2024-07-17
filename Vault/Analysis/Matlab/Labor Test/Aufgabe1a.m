% Aufgabe 1a.m
% 11.05.2024
clc, clearvars, close all

x = linspace(0, 10, 1000 + 1);

alpha = 2;
beta = 1;

a = @(n) (1+1./n).^n;
b = @(n) (1+1./n).^(n+1);
c = @(n) (n.^alpha ./ log(n));
d = @(n) (n.^beta ./ exp(1).^n);
gy = @(n) (1./n .* cos(n));
gz = @(n) (1./n .* sin(n));

subplot(3, 2, 1);
line1 = plot(x, a(x), Color='r', LineStyle='-',DisplayName='A(n)');
title('A(x)');

subplot(3, 2, 2);
line2 = plot(x, b(x), Color='g', LineStyle='-',DisplayName='B(n)');
title('B(x)');

subplot(3, 2, 3);
line3 = plot(x, c(x), Color='b', LineStyle='-',DisplayName='C(n)');
title('C(x)');

subplot(3, 2, 4);
line4 = plot(x, d(x), Color='y', LineStyle='-',DisplayName='D(n)');
title('D(x)');

subplot(3, 2, 5);
line5 = plot3(x, gy(x), gz(x), Color='m', LineStyle='-',DisplayName='g(n)');
title('G(x)');

leg = subplot(3, 2, 6, 'Visible', 'off');
legPos = leg.Position;
delete(leg);

lg = legend([line1, line2, line3, line4, line5]);
lg.Position(1:2) = legPos(1:2);