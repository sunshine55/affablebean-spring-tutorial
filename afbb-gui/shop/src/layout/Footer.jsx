export const Footer = () => {
  return (
    <footer className="footer sm:footer-horizontal footer-center bg-base-300 text-base-content p-4">
      <aside>
        <p>
          Copyright &copy; {new Date().getFullYear()} - Developed by{' '}
          <a
            class="link text-red-500"
            href="https://github.com/sunshine55?tab=repositories"
          >
            sunshine55
          </a>
        </p>
      </aside>
    </footer>
  );
};
