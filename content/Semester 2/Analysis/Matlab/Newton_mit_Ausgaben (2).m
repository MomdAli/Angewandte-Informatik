% Newton_Verfahren.m 
clc, clearvars, close all



% Newton-Verfahren
TOL = 10e-12; % Grenze, wann es aufhören soll

% Funktion eintragen, von der die Nullstelle gesucht ist:
f = @(x) sin(2 * x) - sin(x); %Funktion
% Ableitung der Funktion eintragen
df = @(x) 2 * cos(2 * x) - cos(x); %1. Ableitung 

x0 = (3/2) * pi;        % Startwert x0
it = 0;         % Anzahl durchgeführter Iterationen
fprintf("Iterationen = %d, x0 = %.2e,  |f(x)| = %.2e\n", it, x0, abs(f(x0)));

while (abs(f(x0)) > TOL) && (it < 100)
    x0 = x0 - f(x0)./df(x0);
    it = it + 1;
    fprintf("Iterationen = %d, x0 = %.2e,  |f(x)| = %.2e\n", it, x0, abs(f(x0)));
end


%% Funktionen plotten:
% relevantes Intervall
I = [0 2 * pi];
% x-Werte, für die geplottet wird:
xWerte = linspace(I(1), I(2), 500);
% zu plottende Funktion(en)
a = f;
%b = @(x) ...

plot(xWerte, a(xWerte), "b-" )
hold on
grid on
xlabel("x-Achse")
ylabel("y-Achse")
%plot(xWerte, ..., 'r-')
legend("Funktion f")

%% Funktionen ableiten:

%syms x f(x)
% abzuleitende Funktion
%f(x) = sqrt(x.^2 + (log(x)).^2);
% 1. Ableitung:
%fs(x) = diff(f(x), x)
% 2. Ableitung:
%f2s(x) = diff(f(x), x, 2)