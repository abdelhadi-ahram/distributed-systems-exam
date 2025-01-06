import Link from "next/link";
import { CreditCard } from "lucide-react";

export function Logo({ withText = false }: { withText?: boolean }) {
  return (
    <Link href="/" prefetch={false} className="flex items-center space-x-2">
      <CreditCard className="text-primary" />
    </Link>
  )
}