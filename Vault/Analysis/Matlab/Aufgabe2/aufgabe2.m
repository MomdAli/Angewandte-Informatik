% aufgabe2.m 
% Datum: 02.07.2024
clc, clearvars, close all

newton(35, 50, 10, 4, 1) % Funktion aufruf
newton(35, 50, 10, 5, 1) % Funktion aufruf
newton(35, 50, 30, 4, 1) % Funktion aufruf
newton(35, 50, 5, 4, 1) % Funktion aufruf

function newton(A, B, C, D, E)
    % Rechnungen
    syms y
    T(y) = (sqrt(C^2 + y^2)/D) + (sqrt(A^2 + (B - y)^2)/E);
    f(y) = diff(T(y), y);
    df(y) = diff(f(y), y);
    
    % Newton-Verfahren
    x = B/2;
    TOL = 1.0e-08;
    res = abs(f(x));
    iter = 0;    
    while res>=TOL & iter<100
        x = double(x - f(x)/df(x)); % xk+1 = xk - f(xk)/df(xk)
        res = abs(double(f(x)));
        iter = iter + 1;
    end

    % Ergebnis Ausgeben
    if (isinf(x) || iter >= 100)
        error('Geschätze Wert ist nicht vernunftigen');
    end
    fprintf('A=%.2f B=%.2f C=%.2f D=%.2f E=%.2f\n', A, B, C, D, E);
    fprintf('Der optimale Punkt entlang des Ufers ist: %.3f Meter\n', x);
    fprintf('Die minimale Zeit beträgt: %.3f Sekunden\n\n', T(x));  
    
    % % Plotten   
    % % Plot 1
    % subplot(2, 2, 1);
    % plot(-A, B, 'bx', MarkerSize=8); % ertrinkende Person
    % hold on
    % plot(C, 0, 'rx', MarkerSize=8); % Rettungsschwimmerin
    % plot([0 0], [0 B], '-g'); % Ufer
    % plot([C 0], [0 x], '-', Color='#E8B050'); % Punkt von Rs zu y
    % plot([0 -A], [x B], '-c', Color='#5C9FE1'); % Punkt von y zu eP
    % 
    % % Design
    % ax = gca;
    % ax.XAxisLocation = 'origin';
    % ax.YAxisLocation = 'origin';
    % axis([-max(A,C), max(A,C), 0, B]); % Axis size
    % grid on
    % title(sprintf('(vii) Schnellster Weg zu der ertrinkende Person in %.3f Sekunden', T(x)));
    % legend('Ertrinkende Person', 'Rettungsschwimmerin', ...
    %     'Ufer', 'Weg von der Rettungsschwimmerin zum Ufer', ...
    %     'Weg vom Ufer zum Ertrinkenden', fontsize=8);
    % hold off
    
    % Plot 1
    figure
    fplot(T, [0 B]);
    hold on;
    plot(x, T(x), 'ro');
    
    % Design
    xlabel('Distanz entlang des Ufers (Meter)');
    ylabel('Gesamtzeit (Sekunden)');
    title(sprintf('(vi) Laufzeit mit C=%.2f und D=%.2f', C, D));
    grid on;
    hold off;
end