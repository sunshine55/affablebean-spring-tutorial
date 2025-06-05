import Link from 'next/link';

const LinkList = () => {
  return [
    { '/': 'Dashboard' },
    { '/categories': 'Products' },
    { '/orders': 'Orders' },
    { '/customers': 'Customers' },
  ].map((link, index) => {
    const [href, label] = Object.entries(link)[0];
    return (
      <li key={`sidebar-link-${index}`} className="mb-2">
        <Link href={href} className="no-underline text-inherit hover:underline">
          {label}
        </Link>
      </li>
    );
  });
};

export function Sidebar() {
  return (
    <aside className="sidebar">
      <nav>
        <ul className="list-none p-0 m-0">
          <LinkList />
        </ul>
      </nav>
    </aside>
  );
}
