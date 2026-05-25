const root = document.documentElement;
const saved = localStorage.getItem('theme') || 'light';

if (saved === 'dark') {
    root.setAttribute('data-theme', 'dark');
} else {
    root.removeAttribute('data-theme');
}

document.getElementById('themeToggle').addEventListener('click', () => {
    const isDark = root.getAttribute('data-theme') === 'dark';
    const next = isDark ? 'light' : 'dark';
    if (next === 'dark') root.setAttribute('data-theme', 'dark');
    else root.removeAttribute('data-theme');
    localStorage.setItem('theme', next);
});