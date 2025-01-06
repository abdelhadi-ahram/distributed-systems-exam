"use client"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

export default function ListTransfers({ transfers }: { transfers: any }) {
  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>From</TableHead>
          <TableHead>To</TableHead>
          <TableHead>Description</TableHead>
          <TableHead>Amount</TableHead>
          <TableHead>tranfer date</TableHead>
          <TableHead className="text-right">type</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {transfers.map((transfer: any) => (
          <TableRow key={transfer.beneficiaryId}>
            <TableCell>{transfer.beneficiaryId}</TableCell>
            <TableCell>{transfer.sourceIban}</TableCell>
            <TableCell>{transfer.description}</TableCell>
            <TableCell>{transfer.amount}</TableCell>
            <TableCell>{transfer.transferDate}</TableCell>
            <TableCell>{transfer.type}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>

  )
}